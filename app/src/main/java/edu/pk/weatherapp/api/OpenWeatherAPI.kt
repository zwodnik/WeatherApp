package edu.pk.weatherapp.api

import edu.pk.weatherapp.Properties
import edu.pk.weatherapp.model.CurrentWeatherResponse
import edu.pk.weatherapp.model.ForecastWeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherAPI {


    @GET("forecast/daily/")
    fun dailyForecastByCity(@Query("q") cityName : String, @Query("cnt") dayCount : Int = Properties.DEFAULT_FORECAST_DAY, @Query("lang") languageCode : String = Properties.DEFAULT_LANGUAGE_CODE) : Call<ForecastWeatherResponse>

    @GET("forecast/daily/")
    fun dailyForecastByGgraphicCoordinates(@Query("lat") latitude : Double, @Query("lon") longitude : Double, @Query("cnt") dayCount : Int = Properties.DEFAULT_FORECAST_DAY, @Query("lang") languageCode : String = Properties.DEFAULT_LANGUAGE_CODE) : Call<ForecastWeatherResponse>


    @GET("weather")
    fun currentWeatherByCity(@Query("q") cityName : String, @Query("lang") languageCode : String = Properties.DEFAULT_LANGUAGE_CODE) : Call<CurrentWeatherResponse>

    @GET("weather")
    fun currentWeatherByGgraphicCoordinates(@Query("lat") latitude : Double, @Query("lon") longitude : Double,  @Query("lang") languageCode : String = Properties.DEFAULT_LANGUAGE_CODE) : Call<CurrentWeatherResponse>
}