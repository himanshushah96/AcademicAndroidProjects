package a.m.carrental;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import a.m.carrental.db.User;
import a.m.carrental.model.UserModel;

public class SignUpActivity extends Activity {

    TextView textviewSignup;
    TextView textViewCreateAccount;
    EditText edittextFirstname;
    EditText edittextLastname;
    EditText edittextSignupEmail;
    EditText edittextSignupPassword;
    Button buttonRegister;
    TextView textMsg;
    TextView textviewSignupLogin;
    RadioGroup radioGroup_UserType;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final Intent intentToHomeScreen = new Intent(this, HomeActivity.class);
        final Intent intentToLoginScreen = new Intent(this, LoginActivity.class);

        textviewSignup = findViewById(R.id.textView_SignupWelcome);
        textViewCreateAccount = findViewById(R.id.textView_CreateAccountMsg);
        edittextFirstname = findViewById(R.id.editText_fisrtname);
        edittextLastname = findViewById(R.id.editText_lastname);
        edittextSignupEmail = findViewById(R.id.editText_signup_email);
        edittextSignupPassword = findViewById(R.id.editText_signup_password);
        buttonRegister = findViewById(R.id.buttonRegister);
        textMsg = findViewById(R.id.textView_msg);
        textviewSignupLogin = findViewById(R.id.textView_SignUpLogin);
        radioGroup_UserType = findViewById(R.id.radiobuttonGroup_SignUpUserType);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user = new User();
                int selectedId = radioGroup_UserType.getCheckedRadioButtonId();
                radioButton = findViewById(selectedId);

                user.email = edittextSignupEmail.getText().toString();
                user.firstName = edittextFirstname.getText().toString();
                user.lastName = edittextLastname.getText().toString();
                user.password = edittextSignupPassword.getText().toString();
                user.userType = radioButton.getText().toString().toUpperCase();


                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(user.email).matches()){
                    Toast.makeText(getApplicationContext(),"Invalid Email!",Toast.LENGTH_SHORT).show();
                }else{
                    if(UserModel.getUserByEmail(user.email) == null){
                        if(UserModel.Register(user)){
                            intentToHomeScreen.putExtra("userType", user.userType);
                            intentToHomeScreen.putExtra("email", user.email);
                            intentToHomeScreen.putExtra("name", user.firstName + ' '+ user.lastName);
                            startActivity(intentToHomeScreen);
                        }else{
                            Toast.makeText(getApplicationContext(),"Something Went Wrong Please Try again!",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Email is already registered!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        textviewSignupLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentToLoginScreen);
            }
        });
    }
}
