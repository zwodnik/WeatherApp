package edu.pk.weatherapp.model

import com.squareup.moshi.Json

/**
 * Odpowiedz zawierajaca klase miasta oraz liste ze stanem pogody
 * @constructor domyslny
 * @property city klasa miasta
 * @property forecast lista ze stanem pogody
 */
data class ForecastWeatherResponse(@Json(name = "city") var city : City,
                                   @Json(name = "list") var forecast : List<ForecastDetail>)