package com.airmonitor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.example.airmonitor.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Splash Screen
    }

    @Override
    protected void onStart() {
        super.onStart();
        // TODO initialize google api client here
    }
}