package a.m.mad314_pd_1_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
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

import a.m.mad314_pd_1_project.requestmodel.LoginRequestModel;
import a.m.mad314_pd_1_project.responsemodel.LoginResponseModel;
import a.m.mad314_pd_1_project.responsemodel.ResponseModel;
import a.m.mad314_pd_1_project.service.IUserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    Button buttonLogin;
    Intent intentToRegister,intentToHome;
    EditText editTextUsername, editTextPassword;
    TextView textViewRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        intentToHome=new Intent(this,MainActivity.class);
        intentToRegister=new Intent(this,RegisterActivity.class);

        editTextUsername=findViewById(R.id.login_edit_email);
        editTextPassword=findViewById(R.id.login_edit_password);
        buttonLogin=findViewById(R.id.login_button_login);
        textViewRegister=findViewById(R.id.login_text_register);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(editTextUsername.getText().toString()))
                {
                    editTextUsername.setError("Email cannot be empty");
                    editTextUsername.requestFocus();
                }
                else if (TextUtils.isEmpty(editTextPassword.getText().toString())) {
                    editTextPassword.setError("Password cannot be empty");
                    editTextPassword.requestFocus();
                }
                else
                {
                    String email=editTextUsername.getText().toString();
                    String pass=editTextPassword.getText().toString();
                    loginUser(email,pass);


                }
            }
        });


        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentToRegister);
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
    }


    public void loginUser(final String email, String password)
    {
        final IUserService service = RetrofitClient.getRetrofitInstance().create(IUserService.class);

        LoginRequestModel request = new LoginRequestModel();
        request.email = email;
        request.password = password;

        Call<ResponseModel<LoginResponseModel>> call = service.login(request);

        call.enqueue(new Callback<ResponseModel<LoginResponseModel>>() {
            @Override
            public void onResponse(Call<ResponseModel<LoginResponseModel>> call, Response<ResponseModel<LoginResponseModel>> response) {
                ResponseModel<LoginResponseModel> responseModel = response.body();

                if(responseModel != null && responseModel.getError() != null && !responseModel.getError().equals("")){
                    Toast.makeText(getApplicationContext(), responseModel.getError(), Toast.LENGTH_LONG).show();
                }else{
                    LoginResponseModel loginResponse = responseModel.getResponse();

                    UserSession session = UserSession.getInstance();
                    session.setEmail(email);
                    session.setUserId(loginResponse.getUserId());
                    session.setToken(loginResponse.getToken());
                    session.setName(loginResponse.getName());
                    startActivity(intentToHome);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<LoginResponseModel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });
    }


}
