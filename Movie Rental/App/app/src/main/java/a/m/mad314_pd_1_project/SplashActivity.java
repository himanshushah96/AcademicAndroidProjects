package a.m.mad314_pd_1_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH=3000;
    Animation animationTop;
    ImageView imageView;
    UserSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView = findViewById(R.id.splash_logo);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("session", 0); // 0 - for private mode
        session = UserSession.getInstance(preferences);
        animationTop = AnimationUtils.loadAnimation(this,R.anim.splash_logo_in_anim);
        imageView.setAnimation(animationTop);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(!TextUtils.isEmpty(session.getToken()) && !TextUtils.isEmpty(session.getEmail())){
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                }else{
                    startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                    finish();
                }

            }
        },SPLASH_DISPLAY_LENGTH);
    }
}
