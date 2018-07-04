package com.example.rawan.retrieve;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import android.support.v7.widget.Toolbar;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by rawan on 6/5/18.
 */

public class Entries extends MainActivity {

    private Button btnChoose;
    private Uri filePath;
    private FirebaseStorage storage;
    private StorageReference mStorage;
    private DatabaseReference firebasedb;
    private Button  btnUpload;
    private TextView email,name;
    private String down;

    private final int PICK_IMAGE_REQUEST = 71;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entries);
        btnChoose = (Button) findViewById(R.id.btnChoose);
        mStorage = FirebaseStorage.getInstance().getReference();
        firebasedb = FirebaseDatabase.getInstance().getReference();
        btnUpload = (Button) findViewById(R.id.btnUpload);
        email = (EditText) findViewById(R.id.emailEn);
        name = (EditText) findViewById(R.id.nameEn);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String naame = name.getText().toString();
                String emaail = email.getText().toString();
                String downn = down.toString();
                HashMap<String,String> o = new HashMap<>();
                o.put("name",naame);
                o.put("email",emaail);
                o.put("image",downn);
                firebasedb.push().setValue(o).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Entries.this,"Stored..",Toast.LENGTH_LONG).show();

                        }
                        else
                            Toast.makeText(Entries.this,"Error.."+task.getException().toString(),Toast.LENGTH_LONG).show();

                    }
                });
            }
        });


        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null ) {
            Uri uri = data.getData();
            StorageReference ref = mStorage.child("photo").child(uri.getLastPathSegment());
ref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
    @Override
    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
        Toast.makeText(Entries.this, "Uploaded", Toast.LENGTH_SHORT).show();
        Uri Download = taskSnapshot.getDownloadUrl();
        down = Download.toString();
    }
});


        }

}
}
