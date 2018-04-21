package edu.pk.weatherapp

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import edu.pk.weatherapp.api.RestAPI
import edu.pk.weatherapp.model.CurrentWeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import android.util.DisplayMetrics



/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in [WeatherWidgetConfigureActivity]
 */
class WeatherWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        // When the user deletes the widget, delete the preference associated with it.
        for (appWidgetId in appWidgetIds) {
            WeatherWidgetConfigureActivity.deleteTitlePref(context, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {

            val cityName = WeatherWidgetConfigureActivity.loadTitlePref(context, appWidgetId)
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.weather_widget)
            views.setTextViewText(R.id.appwidget_text, cityName)
            val request = RestAPI.openWeatherApi.currentWeatherByCity(cityName)

            request.enqueue(object : Callback<CurrentWeatherResponse> {
                override fun onResponse(call: Call<CurrentWeatherResponse>, response: Response<CurrentWeatherResponse>) {
                    response.body()?.let { weatherResponse ->
                        views.setTextViewText(R.id.appwidget_temperatureText, context.getString(R.string.temperature_degrees_celsius, weatherResponse.main.temperature))
                        appWidgetManager.updateAppWidget(appWidgetId, views)
                    }
                }

                override fun onFailure(call: Call<CurrentWeatherResponse>?, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }

    }


}

