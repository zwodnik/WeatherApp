package edu.pk.weatherapp.model

import com.squareup.moshi.Json

/**
 * Obecny stan pogodowy
 * @constructor domyslny
 * @property cityName nazwa miasta
 * @property sun opisujaca stan slonca
 * @property win opisuje wiatr
 * @property description opis pogodowy
 * @property main podstawowe parametry pogodowe
 */
data class CurrentWeatherResponse(
        @Json(name = "name") var cityName: String,
        @Json(name = "sys") var sun: Sun,
        @Json(name = "wind") var wind: Wind,
        @Json(name = "weather") var description : List<WeatherDescription>,
        @Json(name = "main") var main : WeatherMain
)