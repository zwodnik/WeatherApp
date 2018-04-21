package edu.pk.weatherapp.api

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RestAPI {

    val openWeatherApi: OpenWeatherAPI

    private val apiClient = OkHttpClient.Builder().addInterceptor(OpenWeatherInterceptor()).build()
    private const val BASE_URL = "http://api.openweathermap.org/data/2.5/"

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(apiClient)
                .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build()))
                .build()

        openWeatherApi = retrofit.create(OpenWeatherAPI::class.java)
    }
}