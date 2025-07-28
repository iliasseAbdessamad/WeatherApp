package net.iliasse.weatherapp.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import net.iliasse.weatherapp.util.Constants;

public class RetrofitInstance {
    //Singleton design pattern
    public static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
