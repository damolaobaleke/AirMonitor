package com.softroniiks.airmonitor.models;

public class AqiData {
    String timestamp_utc;
    String timestamp_local;
    String ts; //Unix Timestamp
    int aqi; //Air quality Index;
    double o3; //concentration of surface o3
    double so2; //concentration of surface So2
    double no2;
    double pm10;
    double co;// concentration of carbon monoxide


    public AqiData(String timestamp_utc, String timestamp_local, String ts, int aqi, double o3, double so2, double no2, double pm10, double co) {
        this.timestamp_utc = timestamp_utc;
        this.timestamp_local = timestamp_local;
        this.ts = ts;
        this.aqi = aqi;
        this.o3 = o3;
        this.so2 = so2;
        this.no2 = no2;
        this.pm10 = pm10;
        this.co = co;
    }

    public String getTimestamp_utc() {
        return timestamp_utc;
    }

    public String getTimestamp_local() {
        return timestamp_local;
    }

    public String getTs() {
        return ts;
    }

    public int getAqi() {
        return aqi;
    }

    public double getO3() {
        return o3;
    }

    public double getSo2() {
        return so2;
    }

    public double getNo2() {
        return no2;
    }

    public double getPm10() {
        return pm10;
    }

    public double getCo() {
        return co;
    }
}
