package edu.pk.weatherapp.model

import com.squareup.moshi.Json


data class ForecastWeatherResponse(@Json(name = "city") var city : City,
                                   @Json(name = "list") var forecast : List<ForecastDetail>)