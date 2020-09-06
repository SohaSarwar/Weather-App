package com.example.weatherapp_sohavlog.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("weather?appid=749fd618cebb9ae8b697a9d3092e89a7&units=metric")
    Call<Example> getWeatherData(@Query("q") String name);
}
