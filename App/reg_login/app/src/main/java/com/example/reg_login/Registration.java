package com.example.reg_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reg_login.databinding.ActivityLoginBinding;
import com.example.reg_login.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword;
    Button btnRegister;
    TextView loginNow;
    FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        loginNow = findViewById(R.id.loginNow);
        btnRegister = findViewById(R.id.btnRegister);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
       loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this,Login.class);
                startActivity(intent);
//                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password,email;
                email= editTextEmail.getText().toString();
                password= editTextPassword.getText().toString();
                if (email.isBlank()){
                    editTextEmail.setError("Enter Email");
                    editTextEmail.requestFocus();
                    return;
                }
                if (password.isBlank()){
                    editTextPassword.setError("Enter Password");
                    editTextPassword.requestFocus();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Registration.this, "Account Created",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Registration.this,Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Registration.this, "ERROR : "+task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
}