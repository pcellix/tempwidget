package com.example.wojtekmac.tempwidget1;

import android.os.StrictMode;
import android.util.Log;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by wojtekmac on 20.10.2016.
 */
public class WeatherData {
    private String outTemp_ = "N/A";
    private String outHumid_ = "N/A";
    private String date_ = "N/A";
    private final String apiUrl = "http://vps266604.ovh.net/getWeather";

    public WeatherData() {
        updateWeatherData();
    }

    public String getOutTemp() {
        return outTemp_;
    }

    public String getOutHumid_() {
        return outHumid_;
    }

    public String getDate() { return date_; }

    private boolean parseWeatherData(String strToParse) {
        if (strToParse == null) {
            return false;
        }
        JsonParser jsonParser = new JsonParser();
        JsonElement jelement = jsonParser.parse(strToParse);
        JsonObject jobject = jelement.getAsJsonObject();

        String date = jobject.get("date").toString();

        if (date == null || date.equals(date_)) {
            return false;
        }

        date_ = date;

        String temperature = jobject.get("temperature").toString();
        if (temperature == null) {
            return false;
        }

        outTemp_ = temperature;
        String humidity = jobject.get("humidity").toString();

        if (humidity == null) {
            return false;
        }
        outHumid_ = humidity;
        Log.w("AAA", temperature);
        return true;
    }

    public void updateWeatherData() {
        JsonDownloaderAsync jsonDownloaderAsync = new JsonDownloaderAsync();
        jsonDownloaderAsync.execute(this, apiUrl);
    }

    public void updateWeatherDataSync() {
        String result = JsonDownloaderSync.downloadUrl(apiUrl);
        if (result != null) {
            OnNewDataReceived(result);
        }
    }

    public void OnNewDataReceived(String newData) {
        boolean result = parseWeatherData(newData);
        if (result) {
            MainActivity.OnNewDataToView();
        }

    }

}
