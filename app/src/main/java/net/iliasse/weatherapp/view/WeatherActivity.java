package net.iliasse.weatherapp.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import net.iliasse.weatherapp.databinding.ActivityWeatherBinding;

public class WeatherActivity extends AppCompatActivity {

    ActivityWeatherBinding weatherBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        weatherBinding = ActivityWeatherBinding.inflate(getLayoutInflater());
        setContentView(weatherBinding.getRoot());

        weatherBinding.toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        weatherBinding.search.setOnClickListener(v -> {

        });
    }
}