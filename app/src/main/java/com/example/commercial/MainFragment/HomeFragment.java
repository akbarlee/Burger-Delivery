package com.example.commercial.MainFragment;
import static androidx.fragment.app.FragmentManager.TAG;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.commercial.Adapter.CategoryViewAdapter;
import com.example.commercial.Adapter.ProductViewHolder;
import com.example.commercial.Model.Category;
import com.example.commercial.Model.Product;
import com.example.commercial.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


import java.util.ArrayList;

public class HomeFragment extends Fragment implements CategoryViewAdapter.ItemClickListener {
    LinearLayoutManager mLinearLayoutManager;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
     FirebaseRecyclerAdapter<Product , ProductViewHolder> burger_product , drink_product;
    FirebaseRecyclerOptions<Product> burger_category_options;
    RecyclerView burger_recyclerView , drink_recyclerView;
    CategoryViewAdapter categoryViewAdapter;
    Context myContext;
    Query  categoryQuery , drinkQuery ;
    ArrayList<Category> category_burger_arraylist , category_drink_arraylist;
    CategoryViewAdapter burger_category_adapter , drink_category_adapter;
    DatabaseReference  databaseReference ;
   private RecyclerView category_recyclerView; // For category

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);
        databaseReference = database.getReference("Products");



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
          View view = inflater.inflate(R.layout.fragment_home, container, false);


        // Category listini göstərməkçün
        category_burger_arraylist = new ArrayList<>();


        category_burger_arraylist.add(new Category(R.drawable.ic_hamburger_plain , "Burger", 0));
        category_burger_arraylist.add(new Category(R.drawable.ic_hamburger_plain , "Burger" , 1));
        category_burger_arraylist.add(new Category(R.drawable.ic_hamburger_plain , "Burger", 2));
        category_burger_arraylist.add(new Category(R.drawable.ic_hamburger_plain , "Burger", 3 ));
        category_recyclerView = view.findViewById(R.id.category_list);
        burger_category_adapter = new CategoryViewAdapter(category_burger_arraylist);
        category_recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1 ,GridLayoutManager.HORIZONTAL,false));
        category_recyclerView.setAdapter(burger_category_adapter);
        // --------------------------------------------------------------------

          //  Burger listini göstermekçün
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);
        burger_recyclerView = view.findViewById(R.id.burger_data_list);

        // mRecyclerView.setHasFixedSize(true);
        burger_recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL , false));

        // ---------------------------------------------------------------------------------------

       databaseReference = FirebaseDatabase.getInstance().getReference("Products");

          return view;

    }



    @Override
    public void onStart() {
        super.onStart();
        showData(1);


        burger_product.startListening();
        burger_recyclerView.setAdapter(burger_product);
         //   drink_category.startListening();

        //   drink_recyclerView.setAdapter(drink_category);


    }

    @Override
    public void onStop() {
        super.onStop();
        burger_product.stopListening();
       // drink_category.stopListening();
    }

    @Override
    public void onItemClick(View view, int position) {
       // this.category_index = position;
        showData(position);

    }
    public void showData(int a ){

         switch (a){
             case 1:

                 categoryQuery =databaseReference
                         .orderByChild("category")
                         .equalTo("Sauce" );
                 Log.i("TAG" , categoryQuery + "Sauce Started working");
             burger_category_options = new FirebaseRecyclerOptions.Builder<Product>().setQuery(categoryQuery , Product.class).build();
                 Log.i("TAG" , burger_category_options + "burger_category_options for sauce Started working");
               //  burger_product.updateOptions(burger_category_options);
               //  Log.i("TAG" , burger_product + "update options changed");

             case 2:
                 drinkQuery =databaseReference
                         .orderByChild("category")
                         .equalTo("Drinks" );
                 Log.i("TAG" , categoryQuery + "Drinks Started working");
                 burger_category_options = new FirebaseRecyclerOptions.Builder<Product>().setQuery(drinkQuery , Product.class).build();
                 Log.i("TAG" , burger_category_options + "burger_category_options for drink Started working");
          //     burger_product.updateOptions(burger_category_options);
            //     Log.i("TAG" , burger_product + "update options changed");
               //  burger_product.notifyDataSetChanged();
        /*  drink_category = new FirebaseRecyclerAdapter<Product, ProductViewHolder>(drink_category_options) {
           @Override
            public void onDataChanged() {
                super.onDataChanged();
                // Called each time there is a new query snapshot. You may want to use this method
                // to hide a loading spinner or check for the "no documents" state and update your UI.
                         }

            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Product model) {
                holder.setProductDetails(model.getTitle() , model.getImage());
            }
            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_cardview, parent , false);
                ProductViewHolder productViewHolder = new ProductViewHolder(itemView);

                productViewHolder.setOnClickListener(new ProductViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        // Toast.makeText(MainActivity.this, "Long Click", Toast.LENGTH_SHORT).show();
                    }
                });
                return productViewHolder;
            }
        };*/

        burger_product = new FirebaseRecyclerAdapter<Product, ProductViewHolder>(burger_category_options) {


            @Override
            public void onDataChanged() {
                super.onDataChanged();
                // Called each time there is a new query snapshot. You may want to use this method
                // to hide a loading spinner or check for the "no documents" state and update your UI.

            }

            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Product model) {
                holder.setProductDetails(model.getTitle() , model.getImage());
            }

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_cardview, parent , false);
                ProductViewHolder productViewHolder = new ProductViewHolder(itemView);

                productViewHolder.setOnClickListener(new ProductViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        // Toast.makeText(MainActivity.this, "Long Click", Toast.LENGTH_SHORT).show();
                    }
                });
                return productViewHolder;
            }
        };

    }


} }