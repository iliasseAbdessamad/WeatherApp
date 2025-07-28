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
}
