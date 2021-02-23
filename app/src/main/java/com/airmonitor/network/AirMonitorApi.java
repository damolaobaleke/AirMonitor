package com.airmonitor.network;

import com.airmonitor.models.AqiIndexObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;

public interface AirMonitorApi {

    @GET("/v2.0/forecast/airquality")
    Call<AqiIndexObject> getAqi(@FieldMap HashMap<String, Object> queryParams); //The query map represents a hash map of the query request needed
}
