package edu.pk.weatherapp.model

import com.squareup.moshi.Json


data class City(@Json(name = "name") var cityName : String,
                @Json(name = "country") var country : String)