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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * Created by rawan on 6/4/18.
 */

public class SignUp extends MainActivity{

    private EditText email;
    private EditText password;
    private Button signUpBtn;
    private DatabaseReference firebasedb;
    private FirebaseDatabase database;
    private FirebaseAuth firebaseAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        firebaseAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.EmailEb);
        password = (EditText) findViewById(R.id.Password);
        signUpBtn = (Button) findViewById(R.id.signUp);
        firebasedb = FirebaseDatabase.getInstance().getReference();
signUpBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String emails= email.getText().toString();
        String passwords= password.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(emails,passwords).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(SignUp.this,"Registered",Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(SignUp.this, Home.class));

                }
                else
                    Toast.makeText(SignUp.this,"Can't Registered"+task.getException().toString(),Toast.LENGTH_LONG).show();
            }
        });


    }
});

        /*
signUpBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String naame = name.getText().toString();
        String emaail = email.getText().toString();
        String paassword = password.getText().toString();
        HashMap<String,String> o = new HashMap<>();
        o.put("name",naame);
        o.put("email",emaail);
        o.put("Password",paassword);
        firebasedb.push().setValue(o).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(SignUp.this,"Stored..",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(SignUp.this, Home.class));

                }
                else
                    Toast.makeText(SignUp.this,"Error"+task.getException().toString(),Toast.LENGTH_LONG).show();

            }
        });
    }
});*/
    }
}
