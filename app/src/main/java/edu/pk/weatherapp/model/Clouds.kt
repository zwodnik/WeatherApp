package edu.pk.weatherapp.model

import com.squareup.moshi.Json

/**
 * Opisuje poziom zachmurzenia
 * @constructor domyslny
 * @property cloudiness liczbowa reprezentacja zachmurzenia
 */
data class Clouds(@Json(name = "all") var cloudiness: Int)