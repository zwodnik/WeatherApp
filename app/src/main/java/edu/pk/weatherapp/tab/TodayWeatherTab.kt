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

class TodayWeatherTab : Fragment() {

    val weatherApi = RestAPI().openWeatherApi

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.today_weather_tab, container, false)
        drawCurrentWeather()
        return rootView
    }

    private fun drawCurrentWeather() {
        val request = weatherApi.currentWeather("Krakow")

        request.enqueue(object : Callback<CurrentWeatherResponse> {
            override fun onResponse(call: Call<CurrentWeatherResponse>, response: Response<CurrentWeatherResponse>) {
                response.body()?.let { weatherResponse ->
                    currentTempText.text = "${weatherResponse.main.temperature.toInt()} °C"
                    weather_icon.setIconResource(getString(weatherResponse.description[0].weatherIcon))
                    weatherDetailsText.text = weatherResponse.description[0].description
                }
            }

            override fun onFailure(call: Call<CurrentWeatherResponse>?, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

}
