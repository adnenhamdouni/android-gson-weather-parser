package com.leadertun.gsonandroidweatherparser.parser;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.leadertun.gsonandroidweatherparser.model.WeatherResponseOneDayWrapper;

public class WeatherHelper {

    private  WeatherResponseOneDayWrapper mWeatherWrapper;

    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String BASE_URL_FORECAST = "http://api.openweathermap.org/data/2.5/forecast/daily?q=";
    
    private static String IMG_URL = "http://openweathermap.org/img/w/";
    

//    public static WeatherWrapper getWeather(String data) throws JSONException {
//
//        JSONObject jObj = new JSONObject(data);
//
//        mWeatherWrapper = new WeatherWrapper();
//
//        JSONObject coordObj = getObject("coord", jObj);
//        JSONObject sysObj = getObject("sys", jObj);
//        JSONObject mainObj = getObject("main", jObj);
//        JSONArray jArr = jObj.getJSONArray("weather");
//
//        JSONObject JSONWeather = jArr.getJSONObject(0);
//
//        mWeatherWrapper.setLatitude(getFloat("lat", coordObj));
//        mWeatherWrapper.setLongitude(getFloat("lon", coordObj));
//
//        mWeatherWrapper.setCountry(getString("country", sysObj));
//
//        mWeatherWrapper.setCity(getString("name", jObj));
//
//        mWeatherWrapper.setTemperature(getFloat("temp", mainObj));
//
//        setWeatherIcon(JSONWeather.getInt("id"), jObj.getJSONObject("sys")
//                .getLong("sunrise") * 1000,
//                jObj.getJSONObject("sys").getLong("sunset") * 1000);
//
//        Log.v("adnen", "city = " + mWeatherWrapper.getCity());
//        Log.v("adnen", "country = " + mWeatherWrapper.getCountry());
//        Log.v("adnen", "temerature = " + mWeatherWrapper.getTemperature());
//        Log.v("adnen", "lat = " + mWeatherWrapper.getLatitude());
//        Log.v("adnen", "lon = " + mWeatherWrapper.getLongitude());
//
//        return mWeatherWrapper;
//    }

//    private void setWeatherIcon(int actualId, long sunrise, long sunset) {
//        int id = actualId / 100;
//
//        if (actualId == 800) {
//            long currentTime = new Date().getTime();
//            if (currentTime >= sunrise && currentTime < sunset) {
//                mWeatherWrapper.getWeatherList().get(0).setIcon("weather sunny");
//            } else {
//                mWeatherWrapper.getWeatherList().get(0).setIcon("weather clear night");
//            }
//        } else {
//            switch (id) {
//            case 2:
//                mWeatherWrapper.getWeatherList().get(0).setIcon("weather thunder");
//                break;
//            case 3:
//                mWeatherWrapper.getWeatherList().get(0).setIcon("weather drizzle");
//                break;
//            case 7:
//                mWeatherWrapper.getWeatherList().get(0).setIcon("weather foggy");
//                break;
//            case 8:
//                mWeatherWrapper.getWeatherList().get(0).setIcon("weather cloudy");
//                break;
//            case 6:
//                mWeatherWrapper.getWeatherList().get(0).setIcon("weather snowy");
//                break;
//            case 5:
//                mWeatherWrapper.getWeatherList().get(0).setIcon("weather rainy");
//                break;
//            }
//        }
//        // weatherIcon.setText(icon);
//    }

    private static JSONObject getObject(String tagName, JSONObject jObj)
            throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static String getString(String tagName, JSONObject jObj)
            throws JSONException {
        return jObj.getString(tagName);
    }

    private static float getFloat(String tagName, JSONObject jObj)
            throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    public static String getWeatherData(String location) {
        String result = null;
        HttpURLConnection con = null;
        InputStream is = null;
        try {
            con = (HttpURLConnection) (new URL(BASE_URL + location))
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
        Log.v("adnen", "weather result = " + result);
        return result;

    }

}
