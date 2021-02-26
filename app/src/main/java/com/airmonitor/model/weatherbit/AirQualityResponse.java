package com.airmonitor.model.weatherbit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * This would be based on the Java Api built
 */
@lombok.Getter
@lombok.Setter
public class AirQualityResponse {
    int lat;
    int lon;

    @SerializedName("timezone")
    String timeZone;

    @SerializedName("city_name")
    String cityName;

    @SerializedName("country_code")
    String countryCode;

    @SerializedName("state_code")
    String stateCode;

    @SerializedName("data")
    List<AirQualityMetrics> data;

    public AirQualityResponse(int lat, int lon, String timezone, String city_name, String country_code, String state_code, List<AirQualityMetrics> data) {
        this.lat = lat;
        this.lon = lon;
        this.timeZone = timezone;
        this.cityName = city_name;
        this.countryCode = country_code;
        this.stateCode = state_code;
        this.data = data;

    }
}
