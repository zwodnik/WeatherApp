package edu.pk.weatherapp.model

import com.squareup.moshi.Json


data class ForecastDetail(@Json(name = "dt") var  date: Long,
                          @Json(name = "temp") var temperature : Temperature,
                          @Json(name = "weather") var description : List<WeatherDescription>,
                          @Json(name = "pressure") var pressure : Double,
                          @Json(name = "humidity") var humidity :Double)
