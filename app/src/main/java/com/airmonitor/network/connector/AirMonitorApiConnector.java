package com.airmonitor.network.connector;

import com.airmonitor.model.weatherbit.AirQualityResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface AirMonitorApiConnector {

    @Headers({"Content-Type: application/json;charset=utf-8"})
    @GET("/v2.0/forecast/airquality")
    Call<AirQualityResponse> forecastAQRequest(@Query("latitude") double latitude, @Query("lon") double longitude, @Query("hours") int hours, @Query("key") String apiKey);

    @Headers({"Content-Type: application/json;charset=utf-8"})
    @GET("/v2.0/current/airquality")
    Call<AirQualityResponse> getCurrentAQRequest(@Query("latitude") double latitude, @Query("lon") double longitude, @Query("key") String apiKey);
}
