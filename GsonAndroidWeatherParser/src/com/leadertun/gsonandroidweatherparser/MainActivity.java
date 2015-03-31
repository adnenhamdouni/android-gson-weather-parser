package com.leadertun.gsonandroidweatherparser;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.leadertun.gsonandroidweatherparser.model.WeatherWrapper;
import com.leadertun.gsonandroidweatherparser.parser.WeatherHelper;

public class MainActivity extends ActionBarActivity {

    private TextView mCity;
    private TextView mTemperature;
    private TextView mIcone;
    private ImageView mIconView;

    private WeatherWrapper mWeatherWrapper;

    private String city = "";

    private static final String City = "cityKey";
    private SharedPreferences sharedpreferences;
    private static final String MyPREFERENCES = "MyPrefs";

    private Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);

        initView();
        initSharedPreferences();

        mWeatherWrapper = new WeatherWrapper();
        mWeatherWrapper.setCity(city);

        updateWeatherData(mWeatherWrapper.getCity());

    }

    private void initView() {

        mCity = (TextView) findViewById(R.id.textview_layout_city);
        mTemperature = (TextView) findViewById(R.id.textview_layout_temp);
        mIcone = (TextView) findViewById(R.id.textview_layout_weather_icon);
        mIconView = (ImageView) findViewById(R.id.textview_layout_weather_iconview);

    }

    private void initSharedPreferences() {
        if (sharedpreferences.contains(City)) {
            city = sharedpreferences.getString(City, "");
        } else {

            city = "Sydney,AU";
            editor = sharedpreferences.edit();
            editor.putString(City, city);
            editor.commit();
        }
    }

    private void updateWeatherData(String city) {

        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[] { city });
    }

    private class JSONWeatherTask extends
            AsyncTask<String, Void, WeatherWrapper> {

        @Override
        protected WeatherWrapper doInBackground(String... params) {
            WeatherWrapper weatherWrapper = new WeatherWrapper();
            String data = ((new WeatherHelper()).getWeatherData(params[0]));

            Gson gson = new GsonBuilder().create();
            weatherWrapper = gson.fromJson(data, WeatherWrapper.class);

            // try {
            //
            // weatherWrapper = WeatherHelper.getWeather(data);
            //
            // } catch (JSONException e) {
            // e.printStackTrace();
            // }
            return weatherWrapper;

        }

        @Override
        protected void onPostExecute(WeatherWrapper weatherWrapper) {
            super.onPostExecute(weatherWrapper);

            Log.v("adnen", "Main Activity");
            Log.v("adnen", "Latitude = "
                    + weatherWrapper.getCoordinate().getLatitude());
            Log.v("adnen", "City = " + weatherWrapper.getCity());
            Log.v("adnen", "City = " + weatherWrapper.getSys().getCountry());
            Log.v("adnen", "City = "
                    + weatherWrapper.getMain().getTemperature());

            String city = weatherWrapper.getCity();
            String country = weatherWrapper.getSys().getCountry();
            float temperature = weatherWrapper.getMain().getTemperature();

            // int id = weatherWrapper.getWeatherList().get(0).getId();
            // long sunrise = weatherWrapper.getSys().getSunrise();
            // long sunset = weatherWrapper.getSys().getSunset();

            String icon = weatherWrapper.getWeatherList().get(0).getIcon();

            mCity.setText(city + "," + country);
            mTemperature.setText(Math.round((convertTempCelsius(temperature)))
                    + "°C");

            setWeatherIcon(icon);
            // mIcone.setText(icon);

            Resources res = getResources();
            String mDrawableName = "ic_weather_" + icon;
            int resID = res.getIdentifier(mDrawableName, "drawable",
                    getPackageName());
            Drawable drawable = res.getDrawable(resID);
            mIconView.setImageDrawable(drawable);
            // updateIconeView(id);
        }

    }

    private double convertTempCelsius(double currentTemp) {

        double convertedTemp = currentTemp - 273.15;
        return convertedTemp;
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

    private void setWeatherIcon(String icon) {

        String currentIcon = icon;

        if (currentIcon.equals("01d")) {
            mIcone.setText("sky is clear, day");
        }

        else if (currentIcon.equals("01n")) {
            mIcone.setText("sky is clear, night");
        }

        else if (currentIcon.equals("02d")) {
            mIcone.setText("few clouds, day");
        }

        else if (currentIcon.equals("02n")) {
            mIcone.setText("few clouds, night");
        }

        else if (currentIcon.equals("03d")) {
            mIcone.setText("scattered clouds, day");
        }

        else if (currentIcon.equals("03n")) {
            mIcone.setText("scattered clouds, night");
        }

        else if (currentIcon.equals("04d")) {
            mIcone.setText("broken clouds, day");
        }

        else if (currentIcon.equals("04n")) {
            mIcone.setText("broken clouds, night");
        }

        else if (currentIcon.equals("09d")) {
            mIcone.setText("shower rain, day");
        }

        else if (currentIcon.equals("09n")) {
            mIcone.setText("shower rain, night");
        }

        else if (currentIcon.equals("10d")) {
            mIcone.setText("Rain, day");
        }

        else if (currentIcon.equals("10n")) {
            mIcone.setText("Rain, night");
        }

        else if (currentIcon.equals("11d")) {
            mIcone.setText("Thunderstorm, day");
        }

        else if (currentIcon.equals("11n")) {
            mIcone.setText("Thunderstorm, night");
        }

        else if (currentIcon.equals("13d")) {
            mIcone.setText("snow, day");
        }

        else if (currentIcon.equals("13n")) {
            mIcone.setText("snow, night");
        }

        else if (currentIcon.equals("50d")) {
            mIcone.setText("mist, day");
        }

        else if (currentIcon.equals("50n")) {
            mIcone.setText("mist, night");
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

}
