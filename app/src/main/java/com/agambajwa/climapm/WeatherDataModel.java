package com.agambajwa.climapm;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherDataModel {

    String mTemperature, mCity, mIconName;
    int mCondition;


    public static WeatherDataModel fromJSON(JSONObject jsonObject) throws JSONException {
        try {
            WeatherDataModel weatherDataModel = new WeatherDataModel();

            weatherDataModel.mCity = jsonObject.getString("name");
            weatherDataModel.mCondition = jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");
            weatherDataModel.mIconName = updateWeatherIcon(weatherDataModel.mCondition);

            double tempResult = jsonObject.getJSONObject("main").getDouble("temp") - 273.15;
            int rounded = (int) Math.rint(tempResult);
            weatherDataModel.mTemperature = Integer.toString(rounded);

            return weatherDataModel;
        } catch(JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String updateWeatherIcon(int condition) {

        if (condition >= 0 && condition < 300) {
            return "tstorm1";
        } else if (condition >= 300 && condition < 500) {
            return "light_rain";
        } else if (condition >= 500 && condition < 600) {
            return "shower3";
        } else if (condition >= 600 && condition <= 700) {
            return "snow4";
        } else if (condition >= 701 && condition <= 771) {
            return "fog";
        } else if (condition >= 772 && condition < 800) {
            return "tstorm3";
        } else if (condition == 800) {
            return "sunny";
        } else if (condition >= 801 && condition <= 804) {
            return "cloudy2";
        } else if (condition >= 900 && condition <= 902) {
            return "tstorm3";
        } else if (condition == 903) {
            return "snow5";
        } else if (condition == 904) {
            return "sunny";
        } else if (condition >= 905 && condition <= 1000) {
            return "tstorm3";
        }

        return "dunno";
    }

    public String getmTemperature() {
        return mTemperature + "°";
    }

    public String getmCity() {
        return mCity;
    }

    public String getmIconName() {
        return mIconName;
    }
}
