package edu.pk.weatherapp.api

import edu.pk.weatherapp.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherAPI {

    @GET("forecast/daily/")
    fun dailyForecast(@Query("q") cityName : String, @Query("cnt") dayCount : Int) : Call<WeatherResponse>

}