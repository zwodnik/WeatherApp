package edu.pk.weatherapp.model

import com.squareup.moshi.Json
import java.text.SimpleDateFormat
import java.util.*


data class ForecastDetail(@Json(name = "dt") var date: Long,
                          @Json(name = "temp") var temperature: Temperature,
                          @Json(name = "weather") var description: List<WeatherDescription>,
                          @Json(name = "pressure") var pressure: Double,
                          @Json(name = "speed") var windSpeed: Double,
                          @Json(name = "humidity") var humidity: Double) {

    companion object {
        val DATE_FORMAT = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
    }

    val formatedDate: String by lazy {
        val dateFromTimestamp = Date(date * 1000)
        DATE_FORMAT.format(dateFromTimestamp)
    }
}
