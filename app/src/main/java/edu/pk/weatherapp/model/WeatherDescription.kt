package edu.pk.weatherapp.model

import com.squareup.moshi.Json
import edu.pk.weatherapp.R


class WeatherDescription(
        @Json(name = "main") var main: String,
        @Json(name = "description") var description: String,
        @Json(name = "icon") var icon: String
) {

    val weatherIcon: Int by lazy {
        when (icon) {
            "01d" -> R.string.wi_day_sunny
            "02d" -> R.string.wi_day_cloudy
            "03d" -> R.string.wi_cloud
            "04d" -> R.string.wi_cloudy
            "09d" -> R.string.wi_day_showers
            "10d" -> R.string.wi_day_rain
            "11d" -> R.string.wi_day_thunderstorm
            "13d" -> R.string.wi_day_snow
            "50d" -> R.string.wi_day_fog
            "01n" -> R.string.wi_night_clear
            "02n" -> R.string.wi_night_alt_cloudy
            "03n" -> R.string.wi_cloud
            "04n" -> R.string.wi_cloudy
            "09n" -> R.string.wi_night_alt_showers
            "10n" -> R.string.wi_night_alt_rain
            "11n" -> R.string.wi_night_alt_thunderstorm
            "13n" -> R.string.wi_night_alt_snow
            "50n" -> R.string.wi_night_fog
            else -> R.string.wi_day_cloudy
        }
    }



}