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
                    currentTempText.text =  getString(R.string.temperature_degrees_celsius, weatherResponse.main.temperature.toInt())
                    weather_icon.setIconResource(getString(weatherResponse.description[0].weatherIcon))
                    weatherDetailsText.text = weatherResponse.description[0].description.capitalize()
                    sunRiseText.text = weatherResponse.sun.sunRiseFormattedString
                    sunSetText.text = weatherResponse.sun.sunSetFormattedString
                    pressureText.text = getString(R.string.pressure_value, weatherResponse.main.pressure)
                    minTemperatureText.text = getString(R.string.temperature_degrees_celsius, weatherResponse.main.tempMin)
                    maxTemperatureText.text = getString(R.string.temperature_degrees_celsius, weatherResponse.main.tempMax)
                    humidityText.text = getString(R.string.humidity_value, weatherResponse.main.humidity)
                    windDirectionText.text = getString(R.string.wind_direction_value, weatherResponse.wind.deg)
                    windSpeedText.text = getString(R.string.wind_speed_value, weatherResponse.wind.speed)
                }
            }

            override fun onFailure(call: Call<CurrentWeatherResponse>?, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

}
