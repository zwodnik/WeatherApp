package edu.pk.weatherapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import edu.pk.weatherapp.R
import edu.pk.weatherapp.model.ForecastDetail
import edu.pk.weatherapp.model.WeatherViewHolder


class WeatherRecyclerAdapter(private val context: Context, private val itemList: List<ForecastDetail>?) : RecyclerView.Adapter<WeatherViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): WeatherViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_row, null)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(customViewHolder: WeatherViewHolder, i: Int) {
        itemList?.get(i).let { item ->
            customViewHolder.pressureText.text = context.getString(R.string.pressure_value_with_label, item?.pressure)
            customViewHolder.humidityText.text = context.getString(R.string.humidity_value_with_label, item?.humidity)
            customViewHolder.windSpeedText.text = context.getString(R.string.wind_speed_value_with_label, item?.windSpeed)
            customViewHolder.dateText.text = item?.formatedDate
            item?.description?.let {
                customViewHolder.descriptionTExt.text = it[0].description.capitalize()
                customViewHolder.weatherIcon.text = context.getString(it[0].weatherIcon)
            }
            customViewHolder.temperatureText.text = context.getString(R.string.temperature_degrees_celsius, item?.temperature?.dayTemperature)
        }
    }

    override fun getItemCount(): Int {
        return itemList?.size ?: 0
    }
}
