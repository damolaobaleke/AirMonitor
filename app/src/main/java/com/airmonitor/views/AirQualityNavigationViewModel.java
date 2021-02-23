package com.airmonitor.views;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.airmonitor.models.AqiData;
import com.airmonitor.models.AqiIndexObject;
import com.airmonitor.network.AirMonitorApi;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AirQualityNavigationViewModel extends AndroidViewModel {
    private final static String BASE_URL = "https://api.weatherbit.io";
    AirMonitorApi api;
    private final static String API_KEY = "71d4ab4547ee45c4a321ab11963452d3";  //move into env variable for security
    MutableLiveData<Integer> aqi;

    public AirQualityNavigationViewModel(@NonNull @NotNull Application application) {
        super(application);
        getAQIIndex();
    }

    public void setUpNetworkRequest(Context context){
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

        api = retrofit.create(AirMonitorApi.class);
    }

    public void getAQIIndex(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("lat",30); //TESTING LAT AND LNG
        queryParams.put("lon", 40);
        queryParams.put("key", API_KEY);

        Call<AqiIndexObject> aqiIndexObjectCall = api.getAqi(queryParams);
        aqiIndexObjectCall.enqueue(new Callback<AqiIndexObject>() {
            @Override
            public void onResponse(Call<AqiIndexObject> call, Response<AqiIndexObject> response) {
                //TODO: After Java response Api created based on aqi api
                //AirResponseModel airResponseModel = response.body();

                AqiIndexObject aqiIndexObject = response.body();
                assert aqiIndexObject != null;
                for(AqiData a: aqiIndexObject.getData()){

                    //Log all the aqis in the array
                    Log.i("ANVM", String.valueOf(a.getAqi()));
                    aqi.setValue(a.getAqi());

                }

            }

            @Override
            public void onFailure(Call<AqiIndexObject> call, Throwable t) {
                Log.i("ANVM",t.getLocalizedMessage());

                //Toast.makeText(this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public MutableLiveData<Integer> getAqi() {
        return aqi;
    }
}
