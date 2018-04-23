package edu.pk.weatherapp.model

import com.squareup.moshi.Json

/**
 * Opisuje klasę miasta
 * @constructor domyslny
 * @property cityName nazwa miasta
 * @property sun opisujaca stan slonca
 * @property country nazwa kraju
 */
data class City(@Json(name = "name") var cityName : String,
                @Json(name = "sun") var sun : Sun?,
                @Json(name = "country") var country : String)