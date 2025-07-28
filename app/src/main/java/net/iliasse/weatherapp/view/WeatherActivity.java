package net.iliasse.weatherapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import net.iliasse.weatherapp.R;
import net.iliasse.weatherapp.databinding.ActivityWeatherBinding;
import net.iliasse.weatherapp.model.WeatherModel;
import net.iliasse.weatherapp.util.Constants;
import net.iliasse.weatherapp.viewModel.WeatherViewModel;

public class WeatherActivity extends AppCompatActivity {

    ActivityWeatherBinding weatherBinding;
    String prefer;
    WeatherViewModel weatherViewModel;

    //to find user location
    LocationManager locationManager;
    LocationListener locationListener;
    double lat;
    double lon;

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
                getWeatherDataByLocation();
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

    @SuppressLint("MissingPermission")
    public void getWeatherDataByLocation(){
        weatherBinding.linearLayoutSearch.setVisibility(View.INVISIBLE);

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                lat = location.getLatitude();
                lon = location.getLongitude();

                //just logs user latitude and longitude
                Log.d("user latitude : ", String.valueOf(lat));
                Log.d("user longitude : ", String.valueOf(lon));

                weatherViewModel.getProgressBarLiveData().observe(WeatherActivity.this, progressState -> {
                    if(progressState){
                        weatherBinding.progressBarWeatherData.setVisibility(View.VISIBLE);
                        weatherBinding.linearLayoutWeatherData.setVisibility(View.INVISIBLE);
                    }
                    else{
                        weatherBinding.progressBarWeatherData.setVisibility(View.INVISIBLE);
                    }
                });

                //call the sendRequestByLocation method of the WeatherViewModel
                weatherViewModel.sendRequestByLocation(getApplicationContext(), lat, lon);

                weatherViewModel.getWeatherResponseLiveData().observe(WeatherActivity.this, weatherResponse -> {
                    //show data on user interface
                    showWeatherData(weatherResponse);
                });
            }
        };

        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                500,
                50,
                locationListener);
    }

    public void showWeatherData(WeatherModel response){
        weatherBinding.textViewCityName.setText(response.getName() + ", " + response.getSys().getCountry());
        weatherBinding.textViewTemperature.setText(response.getMain().getTemp() + " °C");
        weatherBinding.textViewWeatherDescription.setText(response.getWeather().get(0).getDescription());
        weatherBinding.textViewhumidity.setText(" : " + response.getMain().getHumidity() + "%");
        weatherBinding.textViewMaxTemp.setText(" : " + response.getMain().getTemp_max() + " °C");
        weatherBinding.textViewMinTemp.setText(" : " + response.getMain().getTemp_min() + " °C");
        weatherBinding.textViewPressure.setText(" : " + response.getMain().getPressure());
        weatherBinding.textViewWind.setText(" : " + response.getWind().getSpeed());

        weatherBinding.progressBarWeatherIcon.setVisibility(View.VISIBLE);
        weatherBinding.linearLayoutWeatherData.setVisibility(View.VISIBLE);

        //https://openweathermap.org/img/wn/01d@2x.png
        String iconCode = response.getWeather().get(0).getIcon();
        Picasso.get().load("https://openweathermap.org/img/wn/" + iconCode + "@2x.png")
                .into(weatherBinding.imageViewWeatherIcon, new Callback() {
                    @Override
                    public void onSuccess() {
                        weatherBinding.progressBarWeatherIcon.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {
                        weatherBinding.imageViewWeatherIcon.setImageResource(R.drawable.partly_cloudy_day);
                        Log.d("iconError", e.getLocalizedMessage());

                        weatherBinding.progressBarWeatherIcon.setVisibility(View.INVISIBLE);
                    }
                });
    }
}