package a.m.mad314_pd_1_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.HashMap;
import java.util.Map;

import a.m.mad314_pd_1_project.requestmodel.RegisterRequestModel;
import a.m.mad314_pd_1_project.responsemodel.ResponseModel;
import a.m.mad314_pd_1_project.service.IUserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText name,email,password,confirmPassword;
    TextView alreadyUser;
    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        alreadyUser = findViewById(R.id.register_text_login);
        signUp = findViewById(R.id.register_button_register);
        name = findViewById(R.id.register_edit_name);
        email = findViewById(R.id.register_edit_email);
        password = findViewById(R.id.register_edit_password);
        confirmPassword = findViewById(R.id.register_edit_confirmPassword);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(email.getText().toString()))
                {
                    email.setError("Email cannot be empty");
                    email.requestFocus();
                }
                else if (TextUtils.isEmpty(password.getText().toString())) {
                    password.setError("Password cannot be empty");
                    password.requestFocus();
                }
                else if (TextUtils.isEmpty(name.getText().toString())){
                    name.setError("Name cannot be empty");
                    name.requestFocus();
                }

                else if (TextUtils.isEmpty(confirmPassword.getText().toString())){
                    confirmPassword.setError("confirm password cannot be empty");
                    confirmPassword.requestFocus();
                }
                else
                {
                    String emailID=email.getText().toString();
                    String pass=password.getText().toString();
                    String nameFromUser = name.getText().toString();
                    String inputConfirmPassword = confirmPassword.getText().toString();
                    if (!pass.equals(inputConfirmPassword))
                    {
                        confirmPassword.setError("confirm does not match");
                        confirmPassword.requestFocus();
                    }
                    else
                    {
                        createUsers(nameFromUser,emailID,pass);
                    }

                }
            }
        });

        alreadyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent _intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(_intent);

            }
        });
        
    }

    public void createUsers(final String name, final String email, String password)
    {
        final IUserService service = RetrofitClient.getRetrofitInstance().create(IUserService.class);

        RegisterRequestModel registerRequestModel = new RegisterRequestModel();
        registerRequestModel.email = email;
        registerRequestModel.name = name;
        registerRequestModel.password = password;

        Call<ResponseModel<String>> call = service.register(registerRequestModel);

        call.enqueue(new Callback<ResponseModel<String>>() {
            @Override
            public void onResponse(Call<ResponseModel<String>> call, Response<ResponseModel<String>> response) {
                ResponseModel<String> apiResponse = response.body();

                if(apiResponse != null && apiResponse.getError() != null && !apiResponse.getError().equals("")){
                    Toast.makeText(getApplicationContext(), apiResponse.getError(), Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), apiResponse.getResponse(), Toast.LENGTH_LONG).show();
                    Intent intentToLogin = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intentToLogin);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<String>> call, Throwable t) {

            }
        });
    }
}
