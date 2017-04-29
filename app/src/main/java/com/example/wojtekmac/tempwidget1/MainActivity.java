package com.example.wojtekmac.tempwidget1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Observable;

public class MainActivity extends AppCompatActivity {
    public static TextView textViewToUpdate = null;
    public static TextView textViewDateToUpdate = null;
    public static Button refreshButton = null;
    public static WeatherData weatherData = new WeatherData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewToUpdate = (TextView)findViewById(R.id.textView);
        textViewDateToUpdate = (TextView)findViewById(R.id.textViewDate);
        refreshButton = (Button) findViewById(R.id.actionButton);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherData.updateWeatherData();
            }
        });
        //Basic AppInit
       // weatherData.updateWeatherData();
    }

    public static void OnNewDataToView() {
        if (textViewToUpdate == null || textViewDateToUpdate == null) {
            return;
        }
        textViewToUpdate.setText(weatherData.getOutTemp());
        textViewDateToUpdate.setText(weatherData.getDate());
    }
}
