package edu.pk.weatherapp.model

import com.squareup.moshi.Json
import edu.pk.weatherapp.R

/**
 * Podstawowe parametry pogodowe
 * @constructor domyslny
 * @property temperature temperatura
 * @property pressure cisnienie
 * @property tempMin temperatura minimalna
 * @property tempMax temperatura maksymalna
 * @property humidity wilgotnosc
 */
class WeatherMain(
        @Json(name = "temp") var temperature: Double,
        @Json(name = "pressure") var pressure: Double,
        @Json(name = "temp_min") var tempMin: Double,
        @Json(name = "temp_max") var tempMax: Double,
        @Json(name = "humidity") var humidity: Double
)
