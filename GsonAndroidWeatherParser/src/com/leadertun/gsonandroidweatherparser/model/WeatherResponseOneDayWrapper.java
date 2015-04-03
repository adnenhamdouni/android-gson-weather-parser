package com.leadertun.gsonandroidweatherparser.model;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class WeatherResponseOneDayWrapper {

    @SerializedName("name")
    private String mCity;
    @SerializedName("coord")
    private Coordinate mCoordinate;
    @SerializedName("sys")
    private Sys mSys;
//    @SerializedName("weather")
//    private Weather mWeather;
    @SerializedName("main")
    private Main mMain;
    @SerializedName("weather")
    private ArrayList<Weather> mWeatherList;

    public WeatherResponseOneDayWrapper() {
    }

    public WeatherResponseOneDayWrapper(String city) {
        
        mCity = city;
    }
    
    public String getCity() {

        return mCity;
    }

    public void setCity(String city) {
        this.mCity = city;
    }

    public Coordinate getCoordinate() {

        return mCoordinate;
    }
    
    public Sys getSys() {
        return mSys;
    }

//    public Weather getWeather() {
//        return mWeather;
//    }
    
    

    public Main getMain() {
        return mMain;
    }

    public ArrayList<Weather> getWeatherList() {
        return mWeatherList;
    }




    public class Coordinate {
        
        @SerializedName("lon")
        private float mLongitude;
        @SerializedName("lat")
        private float mLatitude;

        public Coordinate(float longitude, float latitude) {
            mLongitude = longitude;
            mLatitude = latitude;
        }

        public float getLongitude() {
            return mLongitude;
        }

        public void setLongitude(float longitude) {
            this.mLongitude = longitude;
        }

        public float getLatitude() {
            return mLatitude;
        }

        public void setLatitude(float latitude) {
            this.mLatitude = latitude;
        }

    }
    
    public class Sys {
        
        @SerializedName("id")
        private int mId;
        @SerializedName("type")
        private String mType;
        @SerializedName("message")
        private float mMessage;
        @SerializedName("country")
        private String mCountry;
        @SerializedName("sunrise")
        private long mSunrise;
        @SerializedName("sunset")
        private long mSunset;
        
        public Sys(int id, String type, float message, String country,
                long sunrise, long sunset) {
            
            mId = id;
            mType = type;
            mMessage = message;
            mCountry = country;
            mSunrise = sunrise;
            mSunset = sunset;
        }
        
        public int getId() {
            return mId;
        }
        public void setId(int id) {
            this.mId = id;
        }
        public String getType() {
            return mType;
        }
        public void setType(String type) {
            this.mType = type;
        }
        public float getMessage() {
            return mMessage;
        }
        public void setMessage(float message) {
            this.mMessage = message;
        }
        public String getCountry() {
            return mCountry;
        }
        public void setCountry(String country) {
            this.mCountry = country;
        }
        public long getSunrise() {
            return mSunrise;
        }
        public void setSunrise(long sunrise) {
            this.mSunrise = sunrise;
        }
        public long getSunset() {
            return mSunset;
        }
        public void setSunset(long sunset) {
            this.mSunset = sunset;
        }
        
    }
    
    public class Weather {

        @SerializedName("id")
        private int mId;
        @SerializedName("main")
        private String mMain;
        @SerializedName("description")
        private String mDescription;
        @SerializedName("icon")
        private String mIcon;
        
        public Weather(int id, String main, String description, String icon) {
            
            mId = id;
            mMain = main;
            mDescription = description;
            mIcon = icon;
        }
        
        public int getId() {
            return mId;
        }
        public void setId(int id) {
            this.mId = id;
        }
        public String getMain() {
            return mMain;
        }
        public void setMain(String main) {
            this.mMain = main;
        }
        public String getDescription() {
            return mDescription;
        }
        public void setDescription(String description) {
            this.mDescription = description;
        }
        public String getIcon() {
            return mIcon;
        }
        public void setIcon(String icon) {
            this.mIcon = icon;
        }
        
    }
    
    public class Main {

        @SerializedName("temp")
        private float mTemperature;
        @SerializedName("pressure")
        private float mPressure;
        @SerializedName("temp_min")
        private float mTemperatureMin;
        @SerializedName("temp_max")
        private float mTemperatureMax;
        @SerializedName("humidity")
        private float mHumidity;
        
        public Main(float temperature, float pressure, float temperatureMin, float temperatureMax,
                float humidity) {
            
            mTemperature = temperature;
            mPressure = pressure;
            mTemperatureMin = temperatureMin;
            mTemperatureMax = temperatureMax;
            mHumidity = humidity;
        }
        
        public float getTemperature() {
            return mTemperature;
        }
        public void setTemperature(float temp) {
            this.mTemperature = temp;
        }
        public float getPressure() {
            return mPressure;
        }
        public void setPressure(float pressure) {
            this.mPressure = pressure;
        }
        public float getTemperatureMin() {
            return mTemperatureMin;
        }
        public void setTemperatureMin(float temperatureMin) {
            this.mTemperatureMin = temperatureMin;
        }
        public float getTemperatureMax() {
            return mTemperatureMax;
        }
        public void setTemperatureMax(float temperatureMax) {
            this.mTemperatureMax = temperatureMax;
        }
        public float getHumidity() {
            return mHumidity;
        }
        public void setHumidity(float humidity) {
            this.mHumidity = humidity;
        }
        
    }
    
    

}
