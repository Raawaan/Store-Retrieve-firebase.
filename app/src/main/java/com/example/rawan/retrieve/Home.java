package com.example.rawan.retrieve;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rawan on 6/4/18.
 */

public class Home extends MainActivity {
    DatabaseReference firebasedb;
    FirebaseDatabase database;
    RecyclerView.Adapter adapter;
    List<UserData> list;
    RecyclerView rv;
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
     rv = (RecyclerView) findViewById(R.id.RV);
     firebasedb = FirebaseDatabase.getInstance().getReference();

     firebasedb.addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             list = new ArrayList<>();
             for   (DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){
                 UserData userdetails = dataSnapshot1.getValue(UserData.class);
                 UserData listdata = new UserData();
                 String name=userdetails.getName();
                 String image=userdetails.getImage();
                 String emaill=userdetails.getEmail();
                 listdata.setName("Name: "+name);
                 listdata.setImage(image);
                 listdata.setEmail("Email: "+emaill);
                 list.add(listdata);
             }

             RecyclerViewAdapter recycler = new RecyclerViewAdapter(list);
             RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(Home.this);
             rv.setLayoutManager(layoutmanager);
             rv.setItemAnimator(new DefaultItemAnimator());
             rv.setAdapter(recycler);
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {

         }
     });





 }







}
