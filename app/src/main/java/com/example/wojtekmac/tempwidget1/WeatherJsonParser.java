package com.example.wojtekmac.tempwidget1;
import android.util.Log;
import com.google.gson.*;
/**
 * Created by wojtekmac on 20.10.2016.
 */
public class WeatherJsonParser {
    public static WeatherData ParseWeatherData(String strToParse) {
        if (strToParse == null) {
            return null;
        }
        JsonParser jsonParser = new JsonParser();
        JsonElement jelement = jsonParser.parse(strToParse);
        JsonObject  jobject = jelement.getAsJsonObject();

        String temperature = jobject.get("temperature").toString();
        String humidity = jobject.get("humidity").toString();
        String date = jobject.get("date").toString();

        Log.w("AAA", temperature);
        return new WeatherData(temperature, humidity, date);
    }
}
