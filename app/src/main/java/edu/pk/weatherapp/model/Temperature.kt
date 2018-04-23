package edu.pk.weatherapp.model

import com.squareup.moshi.Json

/**
 * Opisuje stan temperatury
 * @constructor domyslny
 * @property dayTemperature temperatura w dzien
 * @property nightTemperature temperatur w nocy
 * @property minTemperature minimalna temperatura
 * @property maxTemperature maksymalna temperatura
 */
data class Temperature (@Json(name = "day") var dayTemperature: Double,
                        @Json(name = "night") var nightTemperature: Double,
                        @Json(name = "min") var minTemperature: Double,
                        @Json(name = "max") var maxTemperature: Double)
