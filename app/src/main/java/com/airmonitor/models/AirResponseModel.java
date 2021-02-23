package com.airmonitor.models;

/**This would be based on the Java Api built*/
public class AirResponseModel {
    private final int status;
    private final String message;
    private final AqiIndexObject aqiData;

    public AirResponseModel(int status, String message, AqiIndexObject aqiData) {
        this.status = status;
        this.message = message;
        this.aqiData = aqiData;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public AqiIndexObject getData() {
        return aqiData;
    }
}
