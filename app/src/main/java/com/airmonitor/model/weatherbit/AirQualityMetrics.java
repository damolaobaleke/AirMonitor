package com.airmonitor.model.weatherbit;

import com.google.gson.annotations.SerializedName;

@lombok.Getter
@lombok.Setter
public class AirQualityMetrics {
    @SerializedName("timestamp_utc")
    String timestampUtc;

    @SerializedName("timestamp_local")
    String timestampLocal;

    @SerializedName("timestamp")
    String timestampUnix;

    @SerializedName("aqi")
    int airQualityIndex; //Air quality Index;

    @SerializedName("o3")
    double o3Concentration; //concentration of surface o3

    @SerializedName("so2")
    double so2Concentration; //concentration of surface So2

    @SerializedName("no2")
    double no2Concentration;

    @SerializedName("pm10")
    double pm10;

    @SerializedName("pm25")
    double pm25;

    @SerializedName("co")
    double co;// concentration of carbon monoxide


    public AirQualityMetrics(String timestamp_utc, String timestamp_local, String ts, int aqi, double o3, double so2, double no2, double pm10, double pm25, double co) {
        this.timestampUtc = timestamp_utc;
        this.timestampLocal = timestamp_local;
        this.timestampUnix = ts;
        this.airQualityIndex = aqi;
        this.o3Concentration = o3;
        this.so2Concentration = so2;
        this.no2Concentration = no2;
        this.pm10 = pm10;
        this.pm25=pm25;
        this.co = co;
    }

}
