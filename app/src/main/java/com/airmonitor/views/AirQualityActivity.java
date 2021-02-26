package com.airmonitor.views;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.customview.widget.Openable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.airmonitor.R;
import com.airmonitor.databinding.ActivityAirQualityNavigationBinding;
import com.google.android.material.navigation.NavigationView;

public class AirQualityActivity extends AppCompatActivity {


    private AppBarConfiguration mAppBarConfiguration;
    private ActivityAirQualityNavigationBinding binding;
    AirQualityNavigationViewModel navigationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAirQualityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarAirQualityNavigation.toolbar);

        //Initialize View Model
        navigationViewModel = new ViewModelProvider(this).get(AirQualityNavigationViewModel.class);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home)
                .setOpenableLayout(new Openable() {
                    @Override
                    public boolean isOpen() {
                        return false;
                    }

                    @Override
                    public void open() {

                    }

                    @Override
                    public void close() {

                    }
                })
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_air_quality_navigation);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //Set up Network request
        navigationViewModel.setUpNetworkRequest(this);

        navigationViewModel.getAqi().observe(() -> null, new Observer<Integer>() {
            @Override
            public void onChanged(Integer aqi) {
                Log.i("AQI", aqi.toString());
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.air_quality_navigation, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_air_quality_navigation);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

}