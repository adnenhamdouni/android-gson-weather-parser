package com.leadertun.gsonandroidweatherparser.model;

import java.util.ArrayList;

public class WeatherWrapper {

    private String mCity;
    private String mCountry;
    private float mLongitude;
    private float mLatitude;
    ArrayList<Prevision> mPrevision;

    public WeatherWrapper() {}
    
    public WeatherWrapper(String city, String country, float longitude,
            float latitude) {
        super();
        this.mCity = city;
        this.mCountry = country;
        this.mLongitude = longitude;
        this.mLatitude = latitude;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        this.mCity = city;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        this.mCountry = country;
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

    public ArrayList<Prevision> getPrevision() {
        return mPrevision;
    }
    
    
    public void setPrevision(ArrayList<Prevision> prevision) {
        this.mPrevision = prevision;
    }



    public static class Prevision {

        private int mId;
        private long mDate;
        private float mMin;
        private float mMax;
        private float mDay;
        private float mNight;
        private float mEvening;
        private float mMorning;
        private String mMain;
        private String mDescription;
        private String mIcon;

        public Prevision() {
        }

        public Prevision(int id, long mDate, float mMin, float mMax,
                float mDay, float mNight, float mEvening, float mMorning,
                String main, String description, String icon) {
            super();
            this.mId = id;
            this.mDate = mDate;
            this.mMin = mMin;
            this.mMax = mMax;
            this.mDay = mDay;
            this.mNight = mNight;
            this.mEvening = mEvening;
            this.mMorning = mMorning;
            this.mMain = main;
            this.mDescription = description;
            this.mIcon = icon;
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

        public long getDate() {
            return mDate;
        }

        public void setDate(long date) {
            this.mDate = date;
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

        public float getDay() {
            return mDay;
        }

        public void setDay(float day) {
            this.mDay = day;
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

        public void setEvening(float evening) {
            this.mEvening = evening;
        }

        public float getMorning() {
            return mMorning;
        }

        public void setMorning(float morning) {
            this.mMorning = morning;
        }

    }

}
