package edu.pk.weatherapp.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.pwittchen.weathericonview.WeatherIconView;

import edu.pk.weatherapp.R;

/**
 * Reprezentacja widoku
 */
public class WeatherViewHolder extends RecyclerView.ViewHolder {
    public TextView dateText;
    public TextView temperatureText;
    public TextView descriptionTExt;
    public TextView windSpeedText;
    public TextView pressureText;
    public TextView humidityText;
    public WeatherIconView weatherIcon;
    public View lineView;

    public WeatherViewHolder(View view) {
        super(view);
        this.dateText = view.findViewById(R.id.itemDate);
        this.temperatureText = view.findViewById(R.id.itemTemperature);
        this.descriptionTExt = view.findViewById(R.id.itemDescription);
        this.windSpeedText = view.findViewById(R.id.itemWind);
        this.pressureText = view.findViewById(R.id.itemPressure);
        this.humidityText = view.findViewById(R.id.itemHumidity);
        this.weatherIcon = view.findViewById(R.id.itemIcon);
        this.lineView = view.findViewById(R.id.lineView);
    }
}