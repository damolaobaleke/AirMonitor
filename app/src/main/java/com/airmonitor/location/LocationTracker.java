package com.airmonitor.location;

import android.location.Location;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;


public class LocationTracker {
    double AGREED_EUCLIDEAN_DISTANCE = 500.0; // 500 meters in all directions
    MutableLiveData<Boolean> shouldChangeLocation = new MutableLiveData<>();

    // todo use this when the location changes past a certain distance,
    //  the observer would most likely call the air quality api to get the value of the air quality index
    // then compare it to the current one to see
    // - what level it is: DANGER, WARNING, GOOD:: Check air quality scale of some sites to get an idea
    // - how much it changed compared to the previous one stored
    public void addLocationChangeCallback(LifecycleOwner owner, Observer<Boolean> observer) {
        shouldChangeLocation.observe(owner, observer);
    }

    public void addLocationChangeCallback(Observer<Boolean> observer) {
        addLocationChangeCallback(() -> null, observer);
    }

    public void checkLocationChange(@NonNull Location oldLocation, @NonNull Location newLocation) {
        float approxDistance = oldLocation.distanceTo(newLocation);
        shouldChangeLocation.postValue(approxDistance > AGREED_EUCLIDEAN_DISTANCE);
    }
}
