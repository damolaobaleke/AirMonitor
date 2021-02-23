package com.airmonitor.models;

import java.util.List;

public class AqiIndexObject {
    int lat;
    int lon;
    String timezone;
    String city_name;
    String country_code;
    String state_code;
    List<AqiData> data;

    public AqiIndexObject(int lat, int lon, String timezone, String city_name, String country_code, String state_code, List<AqiData> data) {
        this.lat = lat;
        this.lon = lon;
        this.timezone = timezone;
        this.city_name = city_name;
        this.country_code = country_code;
        this.state_code = state_code;
        this.data = data;
    }

    public int getLat() {
        return lat;
    }

    public int getLon() {
        return lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public String getState_code() {
        return state_code;
    }

    public List<AqiData> getData() {
        return data;
    }
}
