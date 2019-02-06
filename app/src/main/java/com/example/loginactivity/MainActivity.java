package com.example.loginactivity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText Username, Passowrd;
    private Button Login, Register;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = findViewById(R.id.Username_Button);
        Passowrd = findViewById(R.id.Password_Button);
        Login = findViewById(R.id.Login_Button);
        Register = findViewById(R.id.Reguster_Button);
        bar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        bar.setVisibility(View.GONE);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Username.getText().toString();
                String pass = Passowrd.getText().toString();

                if(name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(name).matches()) {
                    Toast.makeText(MainActivity.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(pass.length() < 5){
                    Toast.makeText(MainActivity.this, "Password Too Short", Toast.LENGTH_SHORT).show();
                    return;
                }

                bar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(name, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Signed Up Successfully", Toast.LENGTH_SHORT).show();
                                    bar.setVisibility(View.GONE);

                                }
                                else if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    bar.setVisibility(View.GONE);

                                }
                                else{
                                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    bar.setVisibility(View.GONE);
                                }
                            }
                        });
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Username.getText().toString();
                String pass = Passowrd.getText().toString();

                if(name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(name).matches()) {
                    Toast.makeText(MainActivity.this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(pass.length() < 5){
                    Toast.makeText(MainActivity.this, "Password Too Short", Toast.LENGTH_SHORT).show();
                    return;
                }

                bar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(name, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                                    bar.setVisibility(View.GONE);
                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    bar.setVisibility(View.GONE);
                                }
                            }
                        });
            }
        });
    }
}

