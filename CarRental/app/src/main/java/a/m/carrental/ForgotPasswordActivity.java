package a.m.carrental;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ForgotPasswordActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        EditText editText_emailForgotPassword = findViewById(R.id.editText_forgot_email);
        Button button_SendLink = findViewById(R.id.button_forgot_sendlink);


    }
}
