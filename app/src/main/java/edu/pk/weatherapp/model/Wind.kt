package edu.pk.weatherapp.model

import com.squareup.moshi.Json

data class Wind(@Json(name = "speed") var speed: Double,
                @Json(name = "deg") var deg: Double = 0.0)