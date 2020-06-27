package com.example.cinemate;

import android.content.Context;
import android.content.Intent;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class RegisterFragment extends Fragment implements View.OnClickListener {
    Button buttonRegister;
    EditText editTextRegisterUsername, editTextRegisterEmail, editTextRegisterPassword, editTextRegisterConfirmPassword;
    TextView textViewLogin;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;


    public RegisterFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        editTextRegisterUsername = view.findViewById(R.id.editText_register_name);
        editTextRegisterEmail = view.findViewById(R.id.editText_register_email);
        editTextRegisterPassword = view.findViewById(R.id.editText_register_password);
        editTextRegisterConfirmPassword = view.findViewById(R.id.editText_register_confirmPassword);
        buttonRegister = view.findViewById(R.id.button_register);
        textViewLogin = view.findViewById(R.id.textView_register_login);

        textViewLogin.setOnClickListener(this);


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkEmptyFields()) {
                    if (editTextRegisterPassword.getText().length() < 6) {
                        editTextRegisterPassword.setError("Password cannot be less than 6 characters");
                        editTextRegisterPassword.requestFocus();
                    } else {
                        if (!editTextRegisterPassword.getText().toString().equals(editTextRegisterConfirmPassword.getText().toString())) {
                            editTextRegisterConfirmPassword.setError("Password not match");
                            editTextRegisterConfirmPassword.requestFocus();

                        } else {
                            String email = editTextRegisterEmail.getText().toString();
                            String password = editTextRegisterPassword.getText().toString();
                            String name = editTextRegisterUsername.getText().toString();

                            createUsers(name, email, password);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    public boolean checkEmptyFields() {


        if (TextUtils.isEmpty(editTextRegisterEmail.getText().toString())) {
            editTextRegisterEmail.setError("Email cannot be empty");
            editTextRegisterEmail.requestFocus();
            return true;
        } else if (TextUtils.isEmpty(editTextRegisterPassword.getText().toString())) {
            editTextRegisterPassword.setError("Password cannot be empty");
            editTextRegisterPassword.requestFocus();
            return true;
        } else if (TextUtils.isEmpty(editTextRegisterConfirmPassword.getText().toString())) {
            editTextRegisterConfirmPassword.setError("Password cannot be empty");
            editTextRegisterConfirmPassword.requestFocus();
            return true;
        } else if (TextUtils.isEmpty(editTextRegisterUsername.getText().toString())) {
            editTextRegisterUsername.setError("Name cannot be empty");
            editTextRegisterUsername.requestFocus();
            return true;
        }
        return false;
    }

    public void createUsers(final String name, final String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    Map<String, Object> usermap = new HashMap<>();
                    usermap.put("name", name);
                    usermap.put("email", email);


                    db.collection("user").document(user.getUid()).set(usermap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getActivity().getApplicationContext(), "Register success", Toast.LENGTH_LONG).show();
                            FirebaseAuth.getInstance().signOut();
                            NavController navController = Navigation.findNavController(getActivity(), R.id.hostFragment);
                            navController.navigate(R.id.loginFragment);
                        }
                    });
                } else {
                    System.out.println("Error" + task.getException());
                }
            }

        }).addOnFailureListener(getActivity(), new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.textView_register_login) {
            NavController navController = Navigation.findNavController(getActivity(), R.id.hostFragment);
            navController.navigate(R.id.loginFragment);
        }
    }
}
