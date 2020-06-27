package a.m.carrental;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import a.m.carrental.db.SetupData;
import a.m.carrental.db.User;
import a.m.carrental.model.Session;
import a.m.carrental.model.UserModel;
import a.m.carrental.model.Constants;

public class LoginActivity extends Activity {

    RadioGroup radioGroup_UserType;
    RadioButton radioButton;
    Intent intentToHomeScreen;
    TextInputEditText editText_LoginEmail;
    TextInputEditText editText_LoginPassword;
    Button button_Login;
    TextView textView_LoginForgotPassword;
    TextView textView_LoginSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        intentToHomeScreen = new Intent(this, HomeActivity.class);

        editText_LoginEmail = findViewById(R.id.editText_login_email);
        editText_LoginPassword = findViewById(R.id.editText_login_password);
        radioGroup_UserType = findViewById(R.id.radiobuttonGroup_userType);

        button_Login = findViewById(R.id.button_Login);
        textView_LoginForgotPassword = findViewById(R.id.textView_LoginForgotPassword);
        textView_LoginSignUp = findViewById(R.id.textView_LoginSignUp);

        button_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = radioGroup_UserType.getCheckedRadioButtonId();
                radioButton = findViewById(selectedId);

                User user = new User();

                user.email = editText_LoginEmail.getText().toString();
                user.password = editText_LoginPassword.getText().toString();
                user.userType = radioButton.getText().toString().toUpperCase();
                UserModel userModel = new UserModel(user.email, user.password, user.userType);
                user = userModel.Login(user.email,user.password,user.userType);

                if (user != null){

                    if(user.userType.equals(Constants.USER_TYPE_CUSTOMER)){
                        intentToHomeScreen.putExtra("userType", Constants.USER_TYPE_CUSTOMER);
                        intentToHomeScreen.putExtra("email", user.email);
                        intentToHomeScreen.putExtra("name", user.firstName + " "+ user.lastName);

                        Session session = Session.getInstance();
                        session.setUserEmail(user.email);
                        session.setUserName(user.firstName + " " + user.lastName);
                        session.setUserType(user.userType);

                        startActivity(intentToHomeScreen);
                        finish();
                    }else{
                        intentToHomeScreen.putExtra("userType", Constants.USER_TYPE_EMPLOYEE);
                        intentToHomeScreen.putExtra("email", user.email);
                        intentToHomeScreen.putExtra("name", user.firstName + " "+ user.lastName);

                        Session session = Session.getInstance();
                        session.setUserEmail(user.email);
                        session.setUserName(user.firstName + " " + user.lastName);
                        session.setUserType(user.userType);

                        startActivity(intentToHomeScreen);
                        finish();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Wrong Credentials",Toast.LENGTH_SHORT).show();
                }

            }
        });

        textView_LoginForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotPassword_intent= new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(forgotPassword_intent);
            }
        });

        textView_LoginSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin_intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(signin_intent);
            }
        });












    }
}
