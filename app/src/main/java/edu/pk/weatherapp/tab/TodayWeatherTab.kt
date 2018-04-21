package edu.pk.weatherapp.tab

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.pk.weatherapp.R
import edu.pk.weatherapp.api.RestAPI
import edu.pk.weatherapp.model.CurrentWeatherResponse
import kotlinx.android.synthetic.main.today_weather_tab.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import edu.pk.weatherapp.MainActivity



class TodayWeatherTab : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.today_weather_tab, container, false)
        val main = activity as MainActivity
        calculateCurrentWeather(main.currentCity)
        return rootView
    }

    fun reloadData(newCity: String) {
        calculateCurrentWeather(newCity)
    }

    fun reloadData(latitude: Double, longitude: Double) {
        calculateCurrentWeather(latitude, longitude)
    }


    private fun enqueueRequest(request: Call<CurrentWeatherResponse>) {
        request.enqueue(object : Callback<CurrentWeatherResponse> {
            override fun onResponse(call: Call<CurrentWeatherResponse>, response: Response<CurrentWeatherResponse>) {
                response.body()?.let { weatherResponse ->
                    currentTempText.text = getString(R.string.temperature_degrees_celsius, weatherResponse.main.temperature)?: ""
                    weather_icon.setIconResource(getString(weatherResponse.description[0].weatherIcon))
                    weatherDetailsText.text = weatherResponse.description[0].description.capitalize()
                    sunRiseText.text = weatherResponse.sun.sunRiseFormattedString
                    sunSetText.text = weatherResponse.sun.sunSetFormattedString
                    pressureText.text = getString(R.string.pressure_value, weatherResponse.main.pressure)?: ""
                    minTemperatureText.text = getString(R.string.temperature_degrees_celsius, weatherResponse.main.tempMin)?: ""
                    maxTemperatureText.text = getString(R.string.temperature_degrees_celsius, weatherResponse.main.tempMax)?: ""
                    humidityText.text = getString(R.string.humidity_value, weatherResponse.main.humidity)?: ""
                    windDirectionText.text = getString(R.string.wind_direction_value, weatherResponse.wind.deg)?: ""
                    windSpeedText.text = getString(R.string.wind_speed_value, weatherResponse.wind.speed)?: ""

                    val main = activity as MainActivity
                    main.currentCity = weatherResponse.cityName
                }
            }

            override fun onFailure(call: Call<CurrentWeatherResponse>?, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun calculateCurrentWeather(city: String) {
        enqueueRequest(RestAPI.openWeatherApi.currentWeatherByCity(city))
    }

    private fun calculateCurrentWeather(latitude: Double, longitude: Double) {
        enqueueRequest(RestAPI.openWeatherApi.currentWeatherByGraphicCoordinates(latitude, longitude))
    }


}
