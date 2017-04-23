package com.example.wojtekmac.tempwidget1;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class JsonDownloader extends AsyncTask<String, Void, String> {
    private String result_ = null;
    public String getResult() { return result_; }

    @Override
    protected String doInBackground(String... params) {
        String urlString = new String(params[0]);
        String result = new String();
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            StringBuilder sbuilder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            while ((result = reader.readLine()) != null) {
                sbuilder.append(result);
            }
            result_ = new String();
            result_ = sbuilder.toString();
        } catch (Exception exception) {
        }
        return result_;
    }


    protected void onPostExecute(String result) {
        if (result_ == null) {
            return;
        }
        Log.w("AAA", result);
        MainActivity.OnNewDataParsed(WeatherJsonParser.ParseWeatherData(result));
    }
}
 