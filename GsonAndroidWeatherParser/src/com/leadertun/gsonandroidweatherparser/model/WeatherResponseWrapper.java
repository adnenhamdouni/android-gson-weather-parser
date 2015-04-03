package com.leadertun.gsonandroidweatherparser.model;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class WeatherResponseWrapper {

    @SerializedName("cod")
    private String mCode;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("city")
    private City mCity;
    @SerializedName("list")
    private ArrayList<List> mList;

    public WeatherResponseWrapper() {
    }

    public WeatherResponseWrapper(String code, String message) {
        mCode = code;
        mMessage = message;
    }

    public String getCode() {

        return mCode;
    }

    public void setCode(String code) {
        this.mCode = code;
    }

    public String getMessage() {

        return mMessage;
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }

    public City getCity() {
        return mCity;
    }

    public ArrayList<List> getLists() {
        return mList;
    }

    public class City {

        @SerializedName("id")
        private int mId;
        @SerializedName("name")
        private String mName;
        @SerializedName("country")
        private String mCountry;
        @SerializedName("coord")
        private Coord mCoord;

        public City(int id, String name, String country) {
            mId = id;
            mName = name;
            mCountry = country;
        }

        public int getId() {
            return mId;
        }

        public void setId(int id) {
            this.mId = id;
        }

        public String getName() {
            return mName;
        }

        public String getCountry() {
            return mCountry;
        }

        public void setmCountry(String country) {
            this.mCountry = country;
        }

        public void setName(String name) {
            this.mName = name;
        }

        public Coord getCoord() {
            return mCoord;
        }

        public class Coord {

            @SerializedName("lon")
            private float mLongitude;
            @SerializedName("lat")
            private float mLatitude;

            public Coord(float longitude, float latitude) {
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

    }

    public class List {

        @SerializedName("dt")
        private long mDate;
        @SerializedName("temp")
        private Temperature mTemperature;
        @SerializedName("weather")
        private ArrayList<Weather> mWeather;

        public List(long date) {

            mDate = date;
        }

        public long getDate() {
            return mDate;
        }

        public void setId(long date) {
            this.mDate = date;
        }

        public Temperature getTemperature() {
            return mTemperature;
        }

        public ArrayList<Weather> getWeathers() {
            return mWeather;
        }

        public class Temperature {

            @SerializedName("day")
            private float mDay;
            @SerializedName("min")
            private float mMin;
            @SerializedName("max")
            private float mMax;
            @SerializedName("night")
            private float mNight;
            @SerializedName("eve")
            private float mEvening;
            @SerializedName("morn")
            private float mMorning;

            public Temperature(float day, float min, float max, float night,
                    float evening, float morning) {

                mDay = day;
                mMin = min;
                mMax = max;
                mNight = night;
                mEvening = evening;
                mMorning = morning;
            }

            public float getDay() {
                return mDay;
            }

            public void setDay(float day) {
                this.mDay = day;
            }

            public float getMin() {
                return mMin;
            }

            public void setMin(float min) {
                this.mMin = min;
            }

            public float getMax() {
                return mMax;
            }

            public void setMax(float max) {
                this.mMax = max;
            }

            public float getNight() {
                return mNight;
            }

            public void setNight(float night) {
                this.mNight = night;
            }

            public float getEvening() {
                return mEvening;
            }

            public void setmEvening(float evening) {
                this.mEvening = evening;
            }

            public float getMorning() {
                return mMorning;
            }

            public void setMorning(float morning) {
                this.mMorning = morning;
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

    }

}
