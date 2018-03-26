package edu.pk.weatherapp.model

import com.squareup.moshi.Json

data class CurrentWeatherResponse(
        @Json(name = "name") var cityName: String,
        @Json(name = "sys") var sun: Sun,
        @Json(name = "wind") var wind: Wind,
        @Json(name = "weather") var description : List<WeatherDescription>,
        @Json(name = "main") var main : WeatherMain
)