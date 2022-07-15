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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
    BottomNavigationView bottomNavigationView;



  CardView b1back , b2back , b3back , b4back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
       fragmentEngineer(new HomeFragment());

 /*  b2back = findViewById(R.id.b2back);
        b2back.setFocusable(true);
        b2back.setFocusableInTouchMode(true);
        b2back.requestFocus();


        b1back = findViewById(R.id.b1back);
        b1back.setFocusable(true);
        b1back.setFocusableInTouchMode(true);
        b1back.requestFocus();*/


  bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
          switch (item.getItemId()) {
              case R.id.home:
                  fragmentEngineer(new HomeFragment());
                  break;
              case R.id.favorite:
                  fragmentEngineer(new SecondFragment());
                  break;
              case R.id.cart:
                  fragmentEngineer(new ThirdFragment());
                  break;
              case R.id.profile:
                  fragmentEngineer(new FourthFragment());
                  break;
          }
          return true;
      }
  });


    }

        private  void fragmentEngineer(Fragment fragment) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_container,fragment);
            fragmentTransaction.commit();
        }







}



