package edu.pk.weatherapp.model

import com.squareup.moshi.Json

data class Clouds(@Json(name = "all") var cloudiness: Int)