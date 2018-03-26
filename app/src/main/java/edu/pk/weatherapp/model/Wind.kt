package edu.pk.weatherapp.model

import com.squareup.moshi.Json

data class Wind(@Json(name = "speed") var speed: Double,
                @Json(name = "deg") var deg: Int = 0)