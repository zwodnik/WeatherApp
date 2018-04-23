package edu.pk.weatherapp.model

import com.squareup.moshi.Json

/**
 * Opisuje stan wiatru
 * @constructor domyslny
 * @property speed szybkosc
 * @property deg kierunek
 */
data class Wind(@Json(name = "speed") var speed: Double,
                @Json(name = "deg") var deg: Double = 0.0)