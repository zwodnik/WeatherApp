package edu.pk.weatherapp.tab

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.pk.weatherapp.R
import edu.pk.weatherapp.adapter.WeatherRecyclerAdapter
import edu.pk.weatherapp.api.RestAPI
import edu.pk.weatherapp.model.ForecastWeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ForecastWeatherTab : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.forecast_weather_tab, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view?.findViewById<RecyclerView>(R.id.recyclerView)?.let { updateForecast(it) }
    }
    private fun updateForecast(recyclerView: RecyclerView) {
        val request = RestAPI.openWeatherApi.dailyForecast("Krakow")

        request.enqueue(object : Callback<ForecastWeatherResponse> {
            override fun onResponse(call: Call<ForecastWeatherResponse>, response: Response<ForecastWeatherResponse>) {
                response.body()?.let { weatherResponse ->
                    val mainActivity = activity
                    recyclerView.adapter = WeatherRecyclerAdapter(mainActivity, weatherResponse.forecast)
                    recyclerView.layoutManager = LinearLayoutManager(context)
                }
            }

            override fun onFailure(call: Call<ForecastWeatherResponse>?, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}

