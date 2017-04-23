package com.example.wojtekmac.tempwidget1;

/**
 * Created by wojtekmac on 20.10.2016.
 */
public class WeatherData {
    private String outTemp_;
    private String outHumid_;
    private String date_;

    public WeatherData(String outTemp, String outHumid, String date) {
        outTemp_ = outTemp;
        outHumid_ = outHumid;
        date_ = date;
    }

    public String getOutTemp() {
        return outTemp_;
    }

    public String getOutHumid_() {
        return outHumid_;
    }

    public String getDate() { return date_; }

}
