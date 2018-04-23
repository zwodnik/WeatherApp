package edu.pk.weatherapp.tab

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.pk.weatherapp.MainActivity
import edu.pk.weatherapp.R
import edu.pk.weatherapp.adapter.WeatherRecyclerAdapter
import edu.pk.weatherapp.api.RestAPI
import edu.pk.weatherapp.model.ForecastWeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Reprezentacja widoku przezentujacego pogode z danego miasta
 */
class ForecastWeatherTab : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.forecast_weather_tab, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val main = activity as MainActivity
        reloadData(main.currentCity)
    }

    /**
     * Przeladowanie danych na podstawie nazwy miasta
     * @param newCity nazwa miasta
     */
    fun reloadData(newCity: String) {
        view?.findViewById<RecyclerView>(R.id.recyclerView)?.let { calculateForecastWeather(it, newCity) }
    }

    /**
     * Przeladowanie danych na podstawie szerokosci i dlugosci geograficznej
     * @param latitude szerokosc geograficzna
     * @param longitude dlugosc geograficzna
     */
    fun reloadData(latitude: Double, longitude: Double) {
        view?.findViewById<RecyclerView>(R.id.recyclerView)?.let { calculateForecastWeather(it, latitude, longitude) }
    }

    private fun enqueueRequest(recyclerView: RecyclerView, request: Call<ForecastWeatherResponse>) {
        request.enqueue(object : Callback<ForecastWeatherResponse> {
            override fun onResponse(call: Call<ForecastWeatherResponse>, response: Response<ForecastWeatherResponse>) {
                response.body()?.let { weatherResponse ->
                    recyclerView.adapter = WeatherRecyclerAdapter(activity, weatherResponse.forecast)
                    recyclerView.layoutManager = LinearLayoutManager(context)

                    val main = activity as MainActivity
                    main.currentCity = weatherResponse.city.cityName
                }
            }

            override fun onFailure(call: Call<ForecastWeatherResponse>?, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun calculateForecastWeather(recyclerView: RecyclerView, city: String) {
        enqueueRequest(recyclerView, RestAPI.openWeatherApi.dailyForecastByCity(city))
    }

    private fun calculateForecastWeather(recyclerView: RecyclerView, latitude: Double, longitude: Double) {
        enqueueRequest(recyclerView, RestAPI.openWeatherApi.dailyForecastByGraphicCoordinates(latitude, longitude))
    }


}

