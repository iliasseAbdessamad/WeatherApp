package net.iliasse.weatherapp.util;

import android.Manifest;

public class Constants {

    public static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final String nameOfSharedPreferences = "net.iliasse.weatherapp";
    public static final String keyForDeniedAllPermissionsCount = "deniedAllPermissionsCount";
    public static final String keyForDeniedOnlyFinePermissionCount = "deniedOnlyFinePermissionCount";
    public static final String intentName = "weather";
    public static final String byCity = "by city name";
    public static final String byLocation = "by location";
    public static final String BASE_URL = "https://api.openweathermap.org/";
    public static final String SUB_URL = "/data/2.5/weather?appid=18f1a6d8db9f3ffa8e1969298a3f7070&units=metric";
}
