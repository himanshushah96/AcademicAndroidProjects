package a.m.mad314_pd_1_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button login, signUp;
    UserSession session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("session", 0); // 0 - for private mode
        session = UserSession.getInstance(preferences);

        if (!TextUtils.isEmpty(session.getToken()) && !TextUtils.isEmpty(session.getEmail()) && !TextUtils.isEmpty(session.getUserId())) {
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            finish();
        } else {

            login = findViewById(R.id.home_button_login);
            signUp = findViewById(R.id.home_button_register);

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goToLogin = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(goToLogin);
                    finish();
                }
            });

            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goToRegister = new Intent(HomeActivity.this, RegisterActivity.class);
                    startActivity(goToRegister);
                    finish();
                }
            });
        }
    }
}
