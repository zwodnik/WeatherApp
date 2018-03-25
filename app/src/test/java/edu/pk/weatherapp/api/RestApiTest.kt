package edu.pk.weatherapp.api

import org.junit.Assert
import org.junit.Test

class RestApiTest {

    private val weatherApi = RestAPI().openWeatherApi

    @Test
    fun testCurrentWeather() {
        val currentWeatherRequest = weatherApi.currentWeather("Krakow")

        val response = currentWeatherRequest.execute()
        Assert.assertEquals(true, response.isSuccessful)
    }

    @Test
    fun testDailyForecastWeather() {
        val dailyForecastWeatherRequest = weatherApi.dailyForecast("Krakow")

        val response = dailyForecastWeatherRequest.execute()
        Assert.assertEquals(true, response.isSuccessful)
    }

}