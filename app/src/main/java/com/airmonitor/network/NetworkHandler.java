package com.airmonitor.network;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.airmonitor.model.weatherbit.AirQualityResponse;
import com.airmonitor.network.connector.AirMonitorApiConnector;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkHandler {
    private final static String BASE_URL = "https://api.weatherbit.io";

    // TODO, yah damz, we should store the api_key in the environment
    private final static String API_KEY = "71d4ab4547ee45c4a321ab11963452d3";

    private final static String TAG = "NetworkHandler";

    private AirMonitorApiConnector airMonitorApiConnector = null;


    public AirMonitorApiConnector resolveAndCreateApiServiceConnector() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        airMonitorApiConnector = retrofit.create(AirMonitorApiConnector.class);
        return airMonitorApiConnector;
    }

    public AirMonitorApiConnector resolveApiServiceConnector() {
        return (airMonitorApiConnector != null) ? airMonitorApiConnector : resolveAndCreateApiServiceConnector();
    }

    public Callback<AirQualityResponse> apiAsyncCallback(MutableLiveData<AirQualityResponse> storedResponse) {
        return new Callback<>() {
            @Override
            public void onResponse(@NotNull Call<AirQualityResponse> call, @NotNull Response<AirQualityResponse> response) {
                if (response.isSuccessful()) {
                    AirQualityResponse airQualityResponse = response.body();
                    if (airQualityResponse != null) {
                        Log.i(TAG, new Gson().toJson(airQualityResponse)); // log response from weatherbit in json format
                        storedResponse.postValue(airQualityResponse);
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<AirQualityResponse> call, @NotNull Throwable t) {
                Log.e(TAG, t.getLocalizedMessage());
            }
        };
    }

    public void getCurrentAQ(double latitude, double longitude, MutableLiveData<AirQualityResponse> storedResponse) {
        Call<AirQualityResponse> aqCall = resolveApiServiceConnector().getCurrentAQRequest(latitude, longitude, API_KEY);
        aqCall.enqueue(apiAsyncCallback(storedResponse));
    }

    public void getFutureAQ(double latitude, double longitude, int hours, MutableLiveData<AirQualityResponse> storedResponse) {
        if (0 > hours || hours > 72) {
            Log.i(TAG, "hours cannot be less than 0 or more than 72");
            return;
        }
        Call<AirQualityResponse> aqCall = resolveApiServiceConnector().forecastAQRequest(latitude, longitude, hours, API_KEY);
        aqCall.enqueue(apiAsyncCallback(storedResponse));
    }
}
