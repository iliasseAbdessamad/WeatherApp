package net.iliasse.weatherapp.view;

import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import net.iliasse.weatherapp.databinding.ActivityMainBinding;
import net.iliasse.weatherapp.util.Constants;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    ActivityResultLauncher<String[]> permissionsResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //register
        registerForPermission();

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        mainBinding.buttonWeatherByCity.setOnClickListener(v -> {
            //launch the second activity
        });

        mainBinding.buttonWeatherByLocation.setOnClickListener(v -> {
            //ask for permission, open the second activity
            if(hasFineLocationPermission()){
                //checks location, get weather data
            } 
            else if(hasCoarseLocationPermission()){
                //bottom sheet dialog for precise location
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
                        } else if(isCoarseLocationGranted){
                            //bottom sheet dialog for precise location
                        } else{
                            //bottom sheet dialog for permissions
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
}