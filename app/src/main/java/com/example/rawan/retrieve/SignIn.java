package com.example.rawan.retrieve;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * Created by rawan on 6/4/18.
 */

public class SignIn extends MainActivity {
    private EditText email;
    private EditText password;
    private Button SignInbtn;
    private FirebaseAuth firebaseAuth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.sign_in);
        email = (EditText)findViewById(R.id.emailed);
        password=(EditText)findViewById(R.id.passed);
        SignInbtn=(Button) findViewById(R.id.signIn);
        firebaseAuth = FirebaseAuth.getInstance();

        SignInbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             String emails = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                firebaseAuth.signInWithEmailAndPassword(emails,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
if (task.isSuccessful()){
    finish();
    startActivity(new Intent(SignIn.this, Home.class));

}  else
    Toast.makeText(SignIn.this,"Error"+task.getException().toString(),Toast.LENGTH_LONG).show();

                    }

                });
            }
        });
    }}

