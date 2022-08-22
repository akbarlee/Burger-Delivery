package com.example.commercial.MainFragment.HomeFragmentChilds;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.commercial.Adapter.ProductViewHolder;
import com.example.commercial.Model.Product;
import com.example.commercial.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BurgerFragment extends Fragment {
    LinearLayoutManager mLinearLayoutManager;
    RecyclerView mRecyclerView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<Product> options;
    FirebaseRecyclerAdapter<Product , ProductViewHolder> firebaseRecyclerAdapter;
    public BurgerFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);
        databaseReference = database.getReference("Products");
        showData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View burgerView = inflater.inflate(R.layout.fragment_burger, container, false);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);
        mRecyclerView = burgerView.findViewById(R.id.burger_data_list);
        // mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL , false));

        return burgerView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (firebaseRecyclerAdapter != null) {
            firebaseRecyclerAdapter.startListening();
            mRecyclerView.setAdapter(firebaseRecyclerAdapter);
        }
    }
        private void showData() {
            options = new FirebaseRecyclerOptions.Builder<Product>().setQuery(databaseReference , Product.class).build();

            firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Product, ProductViewHolder>(options) {

                @Override
                protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Product model) {
                    holder.setDetails(model.getTitle() , model.getImage());
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
    }
