package com.example.commercial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivity extends AppCompatActivity  {
  RecyclerView dataList;
  Product product;

  private DatabaseReference dataBase;

  CardView b1back , b2back , b3back , b4back;
    private boolean mStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*dataBase = FirebaseDatabase.getInstance().getReference().child("Products");
        dataList = findViewById(R.id.dataList);*/

        b2back = findViewById(R.id.b2back);
        b2back.setFocusable(true);
        b2back.setFocusableInTouchMode(true);
        b2back.requestFocus();


         b1back = findViewById(R.id.b1back);
          b1back.setFocusable(true);
          b1back.setFocusableInTouchMode(true);
          b1back.requestFocus();


    }



}