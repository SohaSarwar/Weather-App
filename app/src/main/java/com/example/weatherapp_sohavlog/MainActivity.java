package com.example.weatherapp_sohavlog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp_sohavlog.Retrofit.ApiClient;
import com.example.weatherapp_sohavlog.Retrofit.ApiInterface;
import com.example.weatherapp_sohavlog.Retrofit.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    ImageView search;
    TextView temp,desc, humidity;
    EditText textfield;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search=findViewById(R.id.search);
        temp=findViewById(R.id.temp);
        desc=findViewById(R.id.desc);
        humidity=findViewById(R.id.humidity);
        textfield=findViewById(R.id.textfield);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getWeatherData(textfield.getText().toString().trim());

            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (textfield.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter City", Toast.LENGTH_SHORT).show();
                } else {
                    getWeatherData(textfield.getText().toString().trim());
                }
            }
        });
    }


    private void getWeatherData(String name)
    {
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<Example> call=apiInterface.getWeatherData(name);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                temp.setText(response.body().getMain().getTemp()+"°C");
                desc.setText("Feels Like"+" "+response.body().getMain().getFeels_like());
                humidity.setText("Humidity"+" "+response.body().getMain().getHumidity());


            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }


}
