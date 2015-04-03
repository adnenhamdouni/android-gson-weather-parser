package com.leadertun.gsonandroidweatherparser.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

public class WeatherResponseHelper {

    private static String BASE_URL;
    private static String BASE_URL_FORECAST;

    public static String getWeatherData(String location) {
        String result = null;
        HttpURLConnection con = null;
        InputStream is = null;
        try {
            BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q="
                    + location;
            con = (HttpURLConnection) (new URL(BASE_URL)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null)
                buffer.append(line + "\r\n");

            is.close();
            con.disconnect();
            result = buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v("adnen", "weather result = " + result);
        return result;

    }

    public static String getWeatherResponseData(String location,
            String numberOfDays) {
        String result = null;
        HttpURLConnection con = null;
        InputStream is = null;
        try {
            BASE_URL_FORECAST = "http://api.openweathermap.org/data/2.5/forecast/daily?q="
                    + location + "&cnt=" + numberOfDays;
            con = (HttpURLConnection) (new URL(BASE_URL_FORECAST))
                    .openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null)
                buffer.append(line + "\r\n");

            is.close();
            con.disconnect();
            result = buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v("adnen", "weather response result = " + result);
        return result;

    }

}
