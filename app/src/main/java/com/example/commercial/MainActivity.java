package com.example.commercial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.cardview.widget.CardView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  {
    RecyclerView recyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;


            Adapter adapter;
    ArrayList<Product> list;



  CardView b1back , b2back , b3back , b4back;
    private boolean mStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.dataList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<Product> options = new FirebaseRecyclerOptions.Builder<Product>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Products").child("Burger"), Product.class)
                .build();

         adapter = new Adapter(options);
         recyclerView.setAdapter(adapter);

        b2back = findViewById(R.id.b2back);
        b2back.setFocusable(true);
        b2back.setFocusableInTouchMode(true);
        b2back.requestFocus();


        b1back = findViewById(R.id.b1back);
        b1back.setFocusable(true);
        b1back.setFocusableInTouchMode(true);
        b1back.requestFocus();


        // send Query to Firebase Database
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference().child("Products").child("Burger");


    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    public void  onStop() {
        super.onStop();
        adapter.stopListening();
    }







    }



