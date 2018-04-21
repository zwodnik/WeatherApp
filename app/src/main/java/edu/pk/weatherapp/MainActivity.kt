package edu.pk.weatherapp

import android.Manifest
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.Settings
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import edu.pk.weatherapp.tab.ForecastWeatherTab
import edu.pk.weatherapp.tab.OtherTab
import edu.pk.weatherapp.tab.TodayWeatherTab
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), LocationListener {

    var currentWeatherTab = TodayWeatherTab()
    var forecastWeatherTab = ForecastWeatherTab()
    var otherTab = OtherTab()

    private val PERMISSIONS_ACCESS_FINE_LOCATION = 1
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private var locationManager: LocationManager? = null
    private var progressDialog: ProgressDialog? = null


    var currentCity: String by Delegates.observable(Properties.DEFAULT_CITY) { _, _, new ->
        title = new

        val preferences = PreferenceManager.getDefaultSharedPreferences(this@MainActivity)
        val editor = preferences.edit()
        editor.putString("city", new)
        editor.apply()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferences = PreferenceManager.getDefaultSharedPreferences(this@MainActivity)
        currentCity = preferences.getString("city", Properties.DEFAULT_CITY)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter
        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_search -> {
                searchCities()
                true
            }
            R.id.action_refresh -> {
                reloadData(currentCity)
                true
            }
            R.id.action_detect_location -> {
                detectLocation()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> currentWeatherTab
                1 -> forecastWeatherTab
                2 -> otherTab
                else -> currentWeatherTab
            }
        }

        override fun getCount(): Int {
            return 3
        }
    }

    private fun searchCities() {
        val alert = AlertDialog.Builder(this)
        alert.setTitle(this.getString(R.string.action_search_city))
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        input.maxLines = 1
        input.setSingleLine(true)
        alert.setView(input)
        alert.setPositiveButton(R.string.ok, { _, _ ->
            val result = input.text.toString()
            if (!result.isEmpty()) {
                saveLocation(result)
            }
        })
        alert.setNegativeButton(R.string.cancel, { _, _ -> })
        alert.show()
    }

    private fun saveLocation(result: String) {
        if (currentCity != result) {
            reloadData(result)
        }
    }

    private fun reloadData(newCity: String) {
        currentWeatherTab.reloadData(newCity)
        forecastWeatherTab.reloadData(newCity)
    }

    private fun reloadData(latitude: Double?, longitude: Double?) {
        if (latitude != null && longitude != null) {
            currentWeatherTab.reloadData(latitude, longitude)
            forecastWeatherTab.reloadData(latitude, longitude)
        }
    }

    private fun detectLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager?.let { locationManager ->
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSIONS_ACCESS_FINE_LOCATION)
                } else{
                }
            } else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                progressDialog = ProgressDialog(this)
                progressDialog?.let { progressDialog ->
                    progressDialog.setMessage(getString(R.string.getting_location))
                    progressDialog.setCancelable(false)
                    progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.cancel), { _, _ ->
                        try {
                            locationManager.removeUpdates(this@MainActivity)
                        } catch (e: SecurityException) {
                            e.printStackTrace()
                        }
                    })
                    progressDialog.show()
                    if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0f, this)
                    }
                    if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
                    }
                }
            } else {
                showLocationSettingsDialog()
            }
        }
    }

    private fun showLocationSettingsDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(R.string.location_settings)
        alertDialog.setMessage(R.string.location_settings_message)
        alertDialog.setPositiveButton(R.string.location_settings_button, { _, _ -> startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)) })
        alertDialog.setNegativeButton(R.string.cancel, { dialog, _ -> dialog.cancel() })
        alertDialog.show()
    }

    override fun onLocationChanged(location: Location?) {
        progressDialog?.hide()
        try {
            locationManager?.removeUpdates(this)
        } catch (e: SecurityException) {
            Log.e("LocationManager", "Error while trying to stop listening for location updates. This is probably a permissions issue", e)
        }

        val latitude = location?.latitude
        val longitude = location?.longitude
        Log.i("LOCATION (${location?.provider?.toUpperCase()} )", "$latitude, $longitude)")
        reloadData(latitude, longitude)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }


}
