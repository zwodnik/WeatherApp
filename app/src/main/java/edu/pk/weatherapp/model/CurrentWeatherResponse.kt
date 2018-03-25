package edu.pk.weatherapp.model

import com.squareup.moshi.Json

data class CurrentWeatherResponse(
        @Json(name = "name") var cityName: String,
        @Json(name = "weather") var description : List<WeatherDescription>,
        @Json(name = "main") var main : WeatherMain
)