package com.example.cinemate;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment implements View.OnClickListener {


    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    Button buttonLogin;
    EditText editTextUsername, editTextPassword;
    TextView textViewRegister;

    public LoginFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        editTextUsername = view.findViewById(R.id.editText_login_username);
        editTextPassword = view.findViewById(R.id.editText_login_password);
        buttonLogin = view.findViewById(R.id.button_login);
        textViewRegister = view.findViewById(R.id.textView_register);


        textViewRegister.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    @Override
    public void onClick(View v) {


        int id = v.getId();
        if (id == R.id.button_login) {
            if (TextUtils.isEmpty(editTextUsername.getText().toString())) {
                editTextUsername.setError("Email cannot be empty");
                editTextUsername.requestFocus();
            } else if (TextUtils.isEmpty(editTextPassword.getText().toString())) {
                editTextPassword.setError("Password cannot be empty");
                editTextPassword.requestFocus();
            } else {
                String email = editTextUsername.getText().toString();
                String pass = editTextPassword.getText().toString();
                loginUser(email, pass);
            }
        } else if (id == R.id.textView_register) {
            NavController navController = Navigation.findNavController(getActivity(), R.id.hostFragment);
            navController.navigate(R.id.registerFragment);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            //Toast.makeText(getActivity().getApplicationContext(),"Already logged in",Toast.LENGTH_LONG).show();
            updateUI(firebaseUser);
        }
    }


    public void loginUser(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            firebaseUser = firebaseAuth.getCurrentUser();
                            Toast.makeText(getActivity().getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
                            updateUI(firebaseUser);
                        }
                    }
                }).addOnFailureListener(getActivity(), new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    public void updateUI(FirebaseUser user) {
        NavController navController = Navigation.findNavController(getActivity(), R.id.hostFragment);
        Bundle bundle = new Bundle();
        bundle.putParcelable("user", user);
        navController.navigate(R.id.dashboardFragment, bundle);
    }
}
