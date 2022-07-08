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

import com.example.commercial.databinding.ActivityMainBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
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
    ActivityMainBinding binding;


  CardView b1back , b2back , b3back , b4back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

  mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);

              mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRecyclerView = findViewById(R.id.dataList);
        mDatabaseReference = mFirebaseDatabase.getReference("Products");
        showData();

      /*  b2back = findViewById(R.id.b2back);
        b2back.setFocusable(true);
        b2back.setFocusableInTouchMode(true);
        b2back.requestFocus();


        b1back = findViewById(R.id.b1back);
        b1back.setFocusable(true);
        b1back.setFocusableInTouchMode(true);
        b1back.requestFocus();*/

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
       getSupportFragmentManager().beginTransaction().replace(R.id.main_container , new FirstFragment()).commit();
      bottomNavigationView.setSelectedItemId(R.id.home);
      bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem item) {
              Fragment fragment = null;
             switch (item.getItemId()) {
                 case R.id.home:
                     fragment = new FirstFragment();
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
          }
      });

    }

    private void showData() {
        options = new FirebaseRecyclerOptions.Builder<Product>().setQuery(mDatabaseReference , Product.class).build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Product, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Product model) {
                holder.setDetails(getApplicationContext(), model.getTitle() , model.getImage());
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_grid_layout , parent , false);
                  ViewHolder viewHolder = new ViewHolder(itemView);
                  viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                      @Override
                      public void onItemClick(View view, int position) {
                         // Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                      }

                      @Override
                      public void onItemLongClick(View view, int position) {
                         // Toast.makeText(MainActivity.this, "Long Click", Toast.LENGTH_SHORT).show();
                      }
                  });
                return  viewHolder;
            }
        };
       GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        firebaseRecyclerAdapter.startListening();
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }


  protected  void onStart() {
        super.onStart();
        if(firebaseRecyclerAdapter != null) {
            firebaseRecyclerAdapter.startListening();
        }
  }





}



