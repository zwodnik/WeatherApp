package edu.pk.weatherapp.model

import com.squareup.moshi.Json
import edu.pk.weatherapp.R

class WeatherMain(
        @Json(name = "temp") var temperature: Double,
        @Json(name = "pressure") var pressure: Int,
        @Json(name = "temp_min") var tempMin: Double,
        @Json(name = "temp_max") var tempMax: Double,
        @Json(name = "humidity") var humidity: Double
)
