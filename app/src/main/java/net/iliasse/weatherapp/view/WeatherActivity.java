package net.iliasse.weatherapp.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import net.iliasse.weatherapp.databinding.ActivityWeatherBinding;
import net.iliasse.weatherapp.util.Constants;
import net.iliasse.weatherapp.viewModel.WeatherViewModel;

public class WeatherActivity extends AppCompatActivity {

    ActivityWeatherBinding weatherBinding;
    String prefer;
    WeatherViewModel weatherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        weatherBinding = ActivityWeatherBinding.inflate(getLayoutInflater());
        setContentView(weatherBinding.getRoot());

        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);

        weatherBinding.linearLayoutWeatherData.setVisibility(View.INVISIBLE);
        prefer = getIntent().getStringExtra(Constants.intentName);

        if(prefer != null){
            if(prefer.equals(Constants.byLocation)){
                weatherBinding.linearLayoutSearch.setVisibility(View.INVISIBLE);
            }
            else{
                weatherBinding.progressBarWeatherData.setVisibility(View.INVISIBLE);
            }
        }


        weatherBinding.toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        weatherBinding.search.setOnClickListener(v -> {

        });
    }
}