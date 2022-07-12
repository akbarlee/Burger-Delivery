package com.example.commercial;



import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import androidx.cardview.widget.CardView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commercial.Adapter.ViewHolder;
import com.example.commercial.Fragment.HomeFragment;
import com.example.commercial.Fragment.FourthFragment;
import com.example.commercial.Fragment.SecondFragment;
import com.example.commercial.Fragment.ThirdFragment;
import com.example.commercial.Model.Product;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class MainActivity extends AppCompatActivity  {

    LinearLayoutManager mLinearLayoutManager;
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    FirebaseRecyclerOptions<Product> options;
    FirebaseRecyclerAdapter<Product , ViewHolder> firebaseRecyclerAdapter;
    BottomNavigationView bottomNavigationView;



  CardView b1back , b2back , b3back , b4back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

  mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);
/*
              mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRecyclerView = findViewById(R.id.dataList);
        mDatabaseReference = mFirebaseDatabase.getReference("Products");*/


      /*  b2back = findViewById(R.id.b2back);
        b2back.setFocusable(true);
        b2back.setFocusableInTouchMode(true);
        b2back.requestFocus();


        b1back = findViewById(R.id.b1back);
        b1back.setFocusable(true);
        b1back.setFocusableInTouchMode(true);
        b1back.requestFocus();*/

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
       getSupportFragmentManager().beginTransaction().replace(R.id.main_container , new HomeFragment()).commit();
      bottomNavigationView.setSelectedItemId(R.id.home);
      bottomNavigationView.setOnItemSelectedListener(item -> {
          Fragment fragment = null;
         switch (item.getItemId()) {
             case R.id.home:
                 fragment = new HomeFragment();
                 break;
             case R.id.favorite:
                 fragment = new SecondFragment();
                 break;
             case R.id.cart:
                 fragment = new ThirdFragment();
                 break;
             case R.id.profile:
                 fragment = new FourthFragment();
                 break;
         }
          getSupportFragmentManager().beginTransaction().replace(R.id.main_container ,fragment).commit();
          return true;
      });

    }









}



