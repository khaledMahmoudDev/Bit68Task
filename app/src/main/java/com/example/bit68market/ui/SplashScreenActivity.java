package com.example.bit68market.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.bit68market.R;

public class SplashScreenActivity extends AppCompatActivity {

    long delayTime = 3000L;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_background);

        handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(getBaseContext(), OnBoardingActivity.class);
            startActivity(intent);
            finish();

        },delayTime);
    }
}
