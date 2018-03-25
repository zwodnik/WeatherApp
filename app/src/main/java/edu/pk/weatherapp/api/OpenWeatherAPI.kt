package edu.pk.weatherapp.api

import edu.pk.weatherapp.model.CurrentWeatherResponse
import edu.pk.weatherapp.model.ForecastWeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherAPI {

    companion object {
        val DEFAULT_FORECAST_DAY = 3
        val DEFAULT_LANGUAGE_CODE = "pl"
    }

    @GET("forecast/daily/")
    fun dailyForecast(@Query("q") cityName : String, @Query("cnt") dayCount : Int = DEFAULT_FORECAST_DAY, @Query("lang") languageCode : String = DEFAULT_LANGUAGE_CODE) : Call<ForecastWeatherResponse>

    @GET("weather")
    fun currentWeather(@Query("q") cityName : String, @Query("lang") languageCode : String = DEFAULT_LANGUAGE_CODE) : Call<CurrentWeatherResponse>

}