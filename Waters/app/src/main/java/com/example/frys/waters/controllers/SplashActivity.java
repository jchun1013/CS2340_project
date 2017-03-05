package com.example.frys.waters.controllers;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.frys.waters.R;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_DURATION = 1000;

    /**
     * OnCreate method required to load activity and loads everything that
     * is needed for the page while setting the view.
     * @param bundle
     */
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_DURATION);
    }
}
