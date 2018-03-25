package edu.pk.weatherapp.model

import com.squareup.moshi.Json


data class Temperature (@Json(name = "day") var dayTemperature: Double,
                        @Json(name = "night") var nightTemperature: Double,
                        @Json(name = "min") var minTemperature: Double,
                        @Json(name = "max") var maxTemperature: Double)
