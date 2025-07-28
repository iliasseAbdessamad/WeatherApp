package net.iliasse.weatherapp.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import net.iliasse.weatherapp.databinding.ActivityMainBinding;
import net.iliasse.weatherapp.databinding.BottomSheetDialogBinding;
import net.iliasse.weatherapp.util.Constants;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    ActivityResultLauncher<String[]> permissionsResultLauncher;
    BottomSheetDialogBinding bottomSheetDialogBinding;

    int deniedAllPermissionsCount;
    int deniedOnlyFinePermissionCount;

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        sharedPreferences = this.getSharedPreferences(Constants.nameOfSharedPreferences, Context.MODE_PRIVATE);
        deniedAllPermissionsCount = sharedPreferences.getInt(Constants.keyForDeniedAllPermissionsCount, 0);
        deniedOnlyFinePermissionCount = sharedPreferences.getInt(Constants.keyForDeniedOnlyFinePermissionCount, 0);


        //register
        registerForPermission();


        mainBinding.buttonWeatherByCity.setOnClickListener(v -> {
            //launch the second activity
            openWeatherActivity(Constants.byCity);
        });

        mainBinding.buttonWeatherByLocation.setOnClickListener(v -> {
            //ask for permission, open the second activity
            if(hasFineLocationPermission()){
                //checks location, get weather data
                checkLocationSettings();
            }
            else if(hasCoarseLocationPermission()){

                saveOnlyDeniedFinePermissionCount();
                if(deniedOnlyFinePermissionCount > 2){
                    checkLocationSettings();
                }
                else{

                    //bottom sheet dialog for precise location
                    showBottomSheetDialog("Give precise location permission for better results.", "fine", "permission");
                }
            }
            else{
                //launch the permissionResultLauncher to request permission dialog
                permissionsResultLauncher.launch(new String[]{Constants.FINE_LOCATION, Constants.COARSE_LOCATION});
            }
        });
    }

    public void registerForPermission(){
        permissionsResultLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                    Boolean b1 = result.get(Constants.FINE_LOCATION);
                    Boolean b2 = result.get(Constants.COARSE_LOCATION);

                    if(b1 != null && b2 != null){
                        boolean isFineLocationGranted = b1;
                        boolean isCoarseLocationGranted = b2;

                        if(isFineLocationGranted){
                            //checks location, get weather data
                            checkLocationSettings();
                        } else if(isCoarseLocationGranted){
                            saveOnlyDeniedFinePermissionCount();

                            //bottom sheet dialog for precise location
                            showBottomSheetDialog("Give precise location permission for better results.", "fine", "permission");
                        } else{
                            deniedAllPermissionsCount++;
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt(Constants.keyForDeniedAllPermissionsCount, 0);
                            editor.apply();

                            //bottom sheet dialog for permissions
                            showBottomSheetDialog("To get the weather by location, you need to enable location permission",
                                    "all", "permission");
                        }
                    }
                });
    }

    private boolean hasFineLocationPermission(){
        return ContextCompat.checkSelfPermission(this, Constants.FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean hasCoarseLocationPermission(){
        return ContextCompat.checkSelfPermission(this, Constants.COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public void showBottomSheetDialog(String message, String deniedPermission, String useFor){
        bottomSheetDialogBinding = BottomSheetDialogBinding.inflate(getLayoutInflater());
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
        bottomSheetDialog.setContentView(bottomSheetDialogBinding.getRoot());

        if(useFor.equals("location")){
            bottomSheetDialogBinding.buttonAllow.setText("Go");
            bottomSheetDialogBinding.textViewTitle.setText("Location");
            bottomSheetDialogBinding.textViewMessage.setText(message);
        }
        else{
            if(deniedAllPermissionsCount > 2 || deniedOnlyFinePermissionCount > 2){
                bottomSheetDialogBinding.buttonAllow.setText("Open");
                bottomSheetDialogBinding.textViewMessage.setText("Open the app settings to give the precise location permission.");
            }
            else{
                bottomSheetDialogBinding.textViewMessage.setText(message);
            }
        }

        bottomSheetDialogBinding.buttonAllow.setOnClickListener(v -> {

            if(useFor.equals("location")){
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
            else{
                if(deniedAllPermissionsCount > 2 || deniedOnlyFinePermissionCount > 2){
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                }
                else{
                    //launch the permissionResultLauncher to request permission dialog
                    permissionsResultLauncher.launch(new String[]{Constants.FINE_LOCATION, Constants.COARSE_LOCATION});
                }
            }

            bottomSheetDialog.dismiss();
        });

        bottomSheetDialogBinding.buttonDeny.setOnClickListener(v -> {

            if(deniedPermission.equals("fine")){
                checkLocationSettings();
            }

            bottomSheetDialog.dismiss();
        });

        bottomSheetDialog.show();
    }

    public void checkLocationSettings(){

        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            //open second activity
            openWeatherActivity(Constants.byLocation);
            //Toast.makeText(this, "Second activity", Toast.LENGTH_SHORT).show();
        }
        else{
            showBottomSheetDialog("Go to the location settings to turn on the location",
                    null,
                    "location");
        }

    }

    public void saveOnlyDeniedFinePermissionCount(){
        deniedOnlyFinePermissionCount++;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constants.keyForDeniedOnlyFinePermissionCount, 0);
        editor.apply();
    }

    public void openWeatherActivity(String prefer){
        Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
        intent.putExtra(Constants.intentName, prefer);

        startActivity(intent);
    }
}