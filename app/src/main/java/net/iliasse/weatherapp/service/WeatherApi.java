package net.iliasse.weatherapp.service;

import net.iliasse.weatherapp.model.WeatherModel;
import net.iliasse.weatherapp.util.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET(Constants.SUB_URL)
    Call<WeatherModel> getWeatherByLocation(@Query("lat") double user_lat, @Query("lon") double user_lon);

    @GET(Constants.SUB_URL)
    Call<WeatherModel> getWeatherByCityName(@Query("q") String city_name);

}
