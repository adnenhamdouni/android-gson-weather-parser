package com.leadertun.gsonandroidweatherparser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.leadertun.gsonandroidweatherparser.model.WeatherResponseOneDayWrapper;
import com.leadertun.gsonandroidweatherparser.model.WeatherResponseWrapper;
import com.leadertun.gsonandroidweatherparser.model.WeatherResponseWrapper.List;
import com.leadertun.gsonandroidweatherparser.model.WeatherWrapper;
import com.leadertun.gsonandroidweatherparser.parser.WeatherHelper;
import com.leadertun.gsonandroidweatherparser.parser.WeatherResponseHelper;

public class MainActivity extends ActionBarActivity implements OnClickListener {

    private TextView mCityTextView;
    private TextView mTemperatureTextView;
    private TextView mIconeTextView;
    private ImageView mIconViewTextView;
    private Button mWyeatherPredictionButton;

    private WeatherResponseOneDayWrapper mWeatherWrapper;

    private WeatherWrapper mWeather;
    private WeatherWrapper.Prevision mPrevision;
    private ArrayList<WeatherWrapper.Prevision> mListPrevisions;

    private String mCity = "";
    private static String mNumberOfDay = "5";

    private static final String City = "cityKey";
    private SharedPreferences sharedpreferences;
    private static final String MyPREFERENCES = "MyPrefs";

    private static final String LOG_TAG = "adnen";

    private Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);

        initView();
        initSharedPreferences();

        mWeather = new WeatherWrapper();

        mListPrevisions = new ArrayList<WeatherWrapper.Prevision>();

        mWeatherWrapper = new WeatherResponseOneDayWrapper();
        mWeatherWrapper.setCity(mCity);

        if (mNumberOfDay == null) {
            Log.v(LOG_TAG, "get only on day weather temperature");
        }

        else {

            Log.v(LOG_TAG, "get weather temperature for " + mNumberOfDay
                    + " days");
        }

        updateWeatherData(mWeatherWrapper.getCity());
        updateWeatherResponseData(mWeatherWrapper.getCity(), mNumberOfDay);

    }

    private void initView() {

        mCityTextView = (TextView) findViewById(R.id.textview_layout_city);
        mTemperatureTextView = (TextView) findViewById(R.id.textview_layout_temp);
        mIconeTextView = (TextView) findViewById(R.id.textview_layout_weather_icon);
        mIconViewTextView = (ImageView) findViewById(R.id.textview_layout_weather_iconview);

        mWyeatherPredictionButton = (Button) findViewById(R.id.button_weather_prediction);
        mWyeatherPredictionButton.setOnClickListener(this);

    }

    private void initSharedPreferences() {
        if (sharedpreferences.contains(City)) {
            mCity = sharedpreferences.getString(City, "");
        } else {

            mCity = "Sydney,AU";
            editor = sharedpreferences.edit();
            editor.putString(City, mCity);
            editor.commit();
        }
    }

    private void updateWeatherData(String city) {

        responseOneDayWeatherTask task = new responseOneDayWeatherTask();
        task.execute(new String[] { city });
    }

    private void updateWeatherResponseData(String city, String numOfDays) {

        weatherResponseTask task = new weatherResponseTask();
        task.execute(new String[] { city, numOfDays });
    }

    private class responseOneDayWeatherTask extends
            AsyncTask<String, Void, WeatherResponseOneDayWrapper> {

        @Override
        protected WeatherResponseOneDayWrapper doInBackground(String... params) {
            WeatherResponseOneDayWrapper weatherWrapper = new WeatherResponseOneDayWrapper();

            String city = params[0];

            String data = ((new WeatherHelper()).getWeatherData(city));

            Gson gson = new GsonBuilder().create();
            weatherWrapper = gson.fromJson(data,
                    WeatherResponseOneDayWrapper.class);

            return weatherWrapper;

        }

        @Override
        protected void onPostExecute(WeatherResponseOneDayWrapper weatherWrapper) {
            super.onPostExecute(weatherWrapper);

            Log.v(LOG_TAG, "Main Activity");
            Log.v(LOG_TAG, "Latitude = "
                    + weatherWrapper.getCoordinate().getLatitude());
            Log.v(LOG_TAG, "City = " + weatherWrapper.getCity());
            Log.v(LOG_TAG, "Country = " + weatherWrapper.getSys().getCountry());
            Log.v(LOG_TAG, "Temperature = "
                    + weatherWrapper.getMain().getTemperature());

            String city = weatherWrapper.getCity();
            String country = weatherWrapper.getSys().getCountry();
            float temperature = weatherWrapper.getMain().getTemperature();

            // int id = weatherWrapper.getWeatherList().get(0).getId();
            // long sunrise = weatherWrapper.getSys().getSunrise();
            // long sunset = weatherWrapper.getSys().getSunset();

            String icon = weatherWrapper.getWeatherList().get(0).getIcon();

            mCityTextView.setText(city + "," + country);
            mTemperatureTextView.setText(Math
                    .round((convertTempCelsius(temperature))) + "°C");

            setWeatherIcon(icon);
            // mIcone.setText(icon);

            updateIconeView(icon);
        }

    }

    private class weatherResponseTask extends
            AsyncTask<String, Void, WeatherResponseWrapper> {

        @Override
        protected WeatherResponseWrapper doInBackground(String... params) {

            WeatherResponseWrapper weatherWrapper = new WeatherResponseWrapper();

            String city = params[0];
            String numberOfDays = params[1];

            String data = ((new WeatherResponseHelper())
                    .getWeatherResponseData(city, numberOfDays));

            Gson gson = new GsonBuilder().create();
            weatherWrapper = gson.fromJson(data, WeatherResponseWrapper.class);

            return weatherWrapper;

        }

        @Override
        protected void onPostExecute(WeatherResponseWrapper weatherWrapper) {
            super.onPostExecute(weatherWrapper);

            mWeather.setCity(weatherWrapper.getCity().getName());
            mWeather.setCountry(weatherWrapper.getCity().getCountry());
            mWeather.setLatitude(weatherWrapper.getCity().getCoord()
                    .getLatitude());
            mWeather.setLongitude(weatherWrapper.getCity().getCoord()
                    .getLongitude());

            for (List WeatherWrapperList : weatherWrapper.getLists()) {

                mPrevision = new WeatherWrapper.Prevision();

                mPrevision.setId(WeatherWrapperList.getWeathers().get(0)
                        .getId());
                mPrevision.setDate(WeatherWrapperList.getDate());
                mPrevision.setDescription(WeatherWrapperList.getWeathers()
                        .get(0).getDescription());
                mPrevision.setMain(WeatherWrapperList.getWeathers().get(0)
                        .getMain());
                mPrevision.setIcon(WeatherWrapperList.getWeathers().get(0)
                        .getIcon());
                mPrevision.setMax(WeatherWrapperList.getTemperature().getMax());
                mPrevision.setMin(WeatherWrapperList.getTemperature().getMin());
                mPrevision.setDay(WeatherWrapperList.getTemperature().getDay());
                mPrevision.setNight(WeatherWrapperList.getTemperature()
                        .getNight());
                mPrevision.setMorning(WeatherWrapperList.getTemperature()
                        .getMorning());
                mPrevision.setEvening(WeatherWrapperList.getTemperature()
                        .getEvening());

                mListPrevisions.add(mPrevision);
            }

            mWeather.setPrevision(mListPrevisions);

            // int id = weatherWrapper.getWeatherList().get(0).getId();
            // long sunrise = weatherWrapper.getSys().getSunrise();
            // long sunset = weatherWrapper.getSys().getSunset();

            String icon = weatherWrapper.getLists().get(0).getWeathers().get(0)
                    .getIcon();

            // setWeatherIcon(icon);
            // mIcone.setText(icon);

            // updateIconeView(icon);
        }

    }

    private double convertTempCelsius(double currentTemp) {

        double convertedTemp = currentTemp - 273.15;

        return convertedTemp;
    }

    public Calendar convertTimestampToCalendar(long dateTimestamp) {

        String result = null;

        Date dateTime = new Date(dateTimestamp);

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(dateTime);

        return calendar;

    }

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change city");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeCity(input.getText().toString());
            }
        });
        builder.show();
    }

    private void updateIconeView(String icon) {

        Resources res = getResources();
        String mDrawableName = "ic_weather_" + icon;
        int resID = res.getIdentifier(mDrawableName, "drawable",
                getPackageName());
        Drawable drawable = res.getDrawable(resID);
        mIconViewTextView.setImageDrawable(drawable);

    }

    private void setWeatherIcon(String icon) {

        String currentIcon = icon;

        if (currentIcon.equals("01d")) {
            mIconeTextView.setText("sky is clear, day");
        }

        else if (currentIcon.equals("01n")) {
            mIconeTextView.setText("sky is clear, night");
        }

        else if (currentIcon.equals("02d")) {
            mIconeTextView.setText("few clouds, day");
        }

        else if (currentIcon.equals("02n")) {
            mIconeTextView.setText("few clouds, night");
        }

        else if (currentIcon.equals("03d")) {
            mIconeTextView.setText("scattered clouds, day");
        }

        else if (currentIcon.equals("03n")) {
            mIconeTextView.setText("scattered clouds, night");
        }

        else if (currentIcon.equals("04d")) {
            mIconeTextView.setText("broken clouds, day");
        }

        else if (currentIcon.equals("04n")) {
            mIconeTextView.setText("broken clouds, night");
        }

        else if (currentIcon.equals("09d")) {
            mIconeTextView.setText("shower rain, day");
        }

        else if (currentIcon.equals("09n")) {
            mIconeTextView.setText("shower rain, night");
        }

        else if (currentIcon.equals("10d")) {
            mIconeTextView.setText("Rain, day");
        }

        else if (currentIcon.equals("10n")) {
            mIconeTextView.setText("Rain, night");
        }

        else if (currentIcon.equals("11d")) {
            mIconeTextView.setText("Thunderstorm, day");
        }

        else if (currentIcon.equals("11n")) {
            mIconeTextView.setText("Thunderstorm, night");
        }

        else if (currentIcon.equals("13d")) {
            mIconeTextView.setText("snow, day");
        }

        else if (currentIcon.equals("13n")) {
            mIconeTextView.setText("snow, night");
        }

        else if (currentIcon.equals("50d")) {
            mIconeTextView.setText("mist, day");
        }

        else if (currentIcon.equals("50n")) {
            mIconeTextView.setText("mist, night");
        }

        // weatherIcon.setText(icon);
    }

    public void changeCity(String city) {

        editor = sharedpreferences.edit();
        editor.putString(City, city);
        editor.commit();

        updateWeatherData(city);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            showInputDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.button_weather_prediction:
            showPrevisions();
            break;
        default:
            break;
        }

    }

    private void showPrevisions() {

        Log.v(LOG_TAG, "show Previsions");

        Log.v(LOG_TAG, "City name = " + mWeather.getCity());
        Log.v(LOG_TAG, "City country = " + mWeather.getCountry());
        Log.v(LOG_TAG, "Latitude = " + mWeather.getLatitude());
        Log.v(LOG_TAG, "Longitude = " + mWeather.getLongitude());

        int i = 0;

        for (WeatherWrapper.Prevision prevision : mWeather.getPrevision()) {

            i++;

            Log.v(LOG_TAG, "Prevision n°" + i);

            Log.v(LOG_TAG, "Date = " + prevision.getDate());
            Log.v(LOG_TAG, "Id = " + prevision.getId());
            Log.v(LOG_TAG, "Main = " + prevision.getMain());
            Log.v(LOG_TAG, "Description = " + prevision.getDescription());
            Log.v(LOG_TAG, "Temp Min = " + prevision.getMin());
            Log.v(LOG_TAG, "Temp Max = " + prevision.getMax());
            Log.v(LOG_TAG, "Temp Day = " + prevision.getDay());
            Log.v(LOG_TAG, "Temp Morning = " + prevision.getMorning());
            Log.v(LOG_TAG, "Temp Evening = " + prevision.getEvening());
            Log.v(LOG_TAG, "Temp Night = " + prevision.getNight());
            Log.v(LOG_TAG, "Icone = " + prevision.getIcon());

            Log.v(LOG_TAG, "-----------------------------------");

        }

        // Calendar calendar = convertTimestampToCalendar(weatherWrapper
        // .getLists().get(0).getDate());
        //
        // int year = calendar.get(Calendar.YEAR);
        // int month = calendar.get(Calendar.MONTH);
        // int date = calendar.get(Calendar.DATE);
        // int day = calendar.get(Calendar.DAY_OF_MONTH);
        // int hour = calendar.get(Calendar.HOUR);
        // int minute = calendar.get(Calendar.MINUTE);
        //
        // Log.v(LOG_TAG, "Date = " + date + " " + hour + ":" + minute);

    }

}
