package com.example.rawan.retrieve;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity  {
private Button SignUpButton;
private Button SignInButton,entries;
    //firebase auth object

    private FirebaseAuth firebaseAuth;

    //view objects
    private TextView textViewUserEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SignUpButton = (Button) findViewById(R.id.SignUp);
        SignInButton =(Button) findViewById(R.id.SignIn);
        entries =(Button) findViewById(R.id.entry);
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        textViewUserEmail.setText("Welcome "+user.getEmail());


     SignInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, SignIn.class));
                }
            });
            SignUpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, SignUp.class));
                }
            });
        entries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Entries.class));
            }
        });
        }
    }

