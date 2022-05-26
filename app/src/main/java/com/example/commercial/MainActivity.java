package com.example.commercial;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
  RecyclerView dataList;
  List<Integer> images;
  List<String> titles;
  Adapter adapter;
  CardView b1back , b2back , b3back , b4back;
    private boolean mStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataList = findViewById(R.id.dataList);

        b2back = findViewById(R.id.b2back);
        b2back.setFocusable(true);
        b2back.setFocusableInTouchMode(true);
        b2back.requestFocus();


         b1back = findViewById(R.id.b1back);
          b1back.setFocusable(true);
          b1back.setFocusableInTouchMode(true);
          b1back.requestFocus();


//         b1back.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public  void onClick(View view) {
//                mStarted = !mStarted;
//                int id1 = b1back.getId();
//
//
//
//                if( mStarted) {
//                    b1back.setBackgroundColor((R.color.black));
//                } else  {
//                    b1back.setBackgroundColor(R.color.purple_200);
//                }
//            }
//        });

//        b2back = findViewById(R.id.b2back);
//        b2back.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//           @Override
//           public void onFocusChange(View view, boolean hasFocus) {
//               mStarted = !mStarted;
//              int id = b2back.getId();
//
//               if(!hasFocus == mStarted) {
//                   b2back.getBackground().setTint(Color.YELLOW);
//               } else {
//                   b2back.getBackground().setTint(Color.RED);
//               }
//           }
//       });
//
//
//        b1back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public  void onClick(View view) {
//
//            }
//        });




        titles = new ArrayList<>();
        images = new ArrayList<>();

        titles.add("First burger");
        titles.add("Second burger");
        titles.add("Third burger");
        titles.add("Fourth burger");
        titles.add("Five burger");
        titles.add("Six burger");

        images.add(R.drawable.image_burger);
        images.add(R.drawable.image_burger_2);
        images.add(R.drawable.image_burger_3);
        images.add(R.drawable.image_burger_4);
        images.add(R.drawable.image_burger_5);
        images.add(R.drawable.image_burger_6);

       adapter = new Adapter(this, titles,images);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL, false);
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(adapter);
    }




//
}