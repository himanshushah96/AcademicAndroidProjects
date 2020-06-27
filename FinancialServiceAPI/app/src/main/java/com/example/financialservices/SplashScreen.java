package com.example.financialservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    Animation animationTop;
    ImageView imageView;

    private final int SPLASH_DISPLAY_LENGTH=2500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setTheme(R.style.splashScreenTheme);
        setContentView(R.layout.activity_splash_screen);

        imageView = findViewById(R.id.imageView_splashLogo);

        animationTop = AnimationUtils.loadAnimation(this,R.anim.splash_logo_in_anim);
        imageView.setAnimation(animationTop);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashScreen.this,MainActivity.class));
                finish();

            }
        },SPLASH_DISPLAY_LENGTH);
    }

    }
