package com.example.commercial;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity  {
    ActivityMainBinding binding;
    LinearLayoutManager mLinearLayoutManager;
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    FirebaseRecyclerOptions<Product> options;
    FirebaseRecyclerAdapter<Product , ViewHolder> firebaseRecyclerAdapter;

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout , fragment);
        fragmentTransaction.commit();
    }


  CardView b1back , b2back , b3back , b4back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new FirstFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.firstFragment:
                            replaceFragment(new FirstFragment());
                            break;
                        case R.id.secondFragment:
                            replaceFragment(new SecondFragment());
                            break;
                        case R.id.thirdFragment:
                            replaceFragment(new ThirdFragment());
                            break;
                        case R.id.fourthFragment:
                            replaceFragment(new FourthFragment());
                            break;
                    }
            return true;
        });

        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);

              mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRecyclerView = findViewById(R.id.dataList);
        mDatabaseReference = mFirebaseDatabase.getReference("Products");
        showData();

        b2back = findViewById(R.id.b2back);
        b2back.setFocusable(true);
        b2back.setFocusableInTouchMode(true);
        b2back.requestFocus();


        b1back = findViewById(R.id.b1back);
        b1back.setFocusable(true);
        b1back.setFocusableInTouchMode(true);
        b1back.requestFocus();




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
                          Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                      }

                      @Override
                      public void onItemLongClick(View view, int position) {
                          Toast.makeText(MainActivity.this, "Long Click", Toast.LENGTH_SHORT).show();
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



