package com.softroniiks.airmonitor;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Splash Screen
        Handler handler  = new Handler();

        Runnable run = new Runnable() {
            @Override
            public void run() {

            }
        };
        handler.postDelayed(run,2000);
    }

    private void loadSignUp(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

}