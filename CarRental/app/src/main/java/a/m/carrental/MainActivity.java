package a.m.carrental;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import a.m.carrental.db.DbModel;
import a.m.carrental.db.SetupData;
import a.m.carrental.db.User;
import a.m.carrental.model.Constants;


public class MainActivity extends Activity {

    Button button_Login;
    Button button_SignUp;
    Intent intentSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_Login = findViewById(R.id.button_MainLogin);
        button_SignUp = findViewById(R.id.button_MainSignUp);

        button_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent Login_intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(Login_intent);
                finish();
            }
        });

        button_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentSignUp = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intentSignUp);
                finish();
            }
        });

    }
}

