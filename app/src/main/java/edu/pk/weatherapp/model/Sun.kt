package edu.pk.weatherapp.model

import android.annotation.SuppressLint
import com.squareup.moshi.Json
import java.text.SimpleDateFormat
import java.util.*

/**
 * Opisuje stan naslonecznienia
 * @constructor domyslny
 * @property sunRiseFormattedString wschod slonca
 * @property sunSetFormattedString zachod slonca
 */
data class Sun(@Json(name = "sunrise") var sunRiseUnixString: Long,
               @Json(name = "sunset") var sunSetUnixString: Long) {


    companion object {
        @SuppressLint("SimpleDateFormat")
        var HOUR_MINUTE_FORMAT = SimpleDateFormat("HH:mm")

        private fun changeUnixTimeFormattedHHmm(unixTime: Long): String {
            val time = Date(unixTime * 1000)
            //HOUR_MINUTE_FORMAT.timeZone = TimeZone.getDefault()
            return HOUR_MINUTE_FORMAT.format(time)
        }
    }

    val sunRiseFormattedString: String by lazy {
        changeUnixTimeFormattedHHmm(sunRiseUnixString)
    }

    val sunSetFormattedString: String by lazy {
        changeUnixTimeFormattedHHmm(sunSetUnixString)
    }
}