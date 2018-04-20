package edu.pk.weatherapp.model

import com.squareup.moshi.Json
import edu.pk.weatherapp.R

class WeatherMain(
        @Json(name = "temp") var temperature: Int,
        @Json(name = "pressure") var pressure: Int,
        @Json(name = "temp_min") var tempMin: Int,
        @Json(name = "temp_max") var tempMax: Int,
        @Json(name = "humidity") var humidity: Int
)
