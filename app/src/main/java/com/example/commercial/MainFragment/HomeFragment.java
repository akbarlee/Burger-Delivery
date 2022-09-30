package com.example.commercial.MainFragment;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    LinearLayoutManager mLinearLayoutManager;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseRecyclerOptions<Product> options;
    FirebaseRecyclerAdapter<Product , ProductViewHolder> firebaseRecyclerAdapter;
    RecyclerView mRecyclerView;
    private View mView;
    Category category;
    ArrayList<Category> item;
    CategoryViewAdapter categoryViewAdapter;
    DatabaseReference burgerReference , databaseReference ;
   private RecyclerView category_recycler; // For category

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
          View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Category listini göstərməkçün
        item = new ArrayList<>();
        item.add(new Category(R.drawable.ic_hamburger_plain , "Burger", 0));
        item.add(new Category(R.drawable.ic_hamburger_plain , "Burger" , 1));
        item.add(new Category(R.drawable.ic_hamburger_plain , "Burger", 2));
        item.add(new Category(R.drawable.ic_hamburger_plain , "Burger", 3 ));
        category_recycler = view.findViewById(R.id.category_list);
        categoryViewAdapter = new CategoryViewAdapter(item);
        category_recycler.setLayoutManager(new GridLayoutManager(getActivity(),1 ,GridLayoutManager.HORIZONTAL,false));
        category_recycler.setAdapter(categoryViewAdapter);
        // --------------------------------------------------------------------

          //  Burger listini göstermekçün
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);
        mRecyclerView = view.findViewById(R.id.burger_data_list);
        // mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL , false));
        // ---------------------------------------------------------------------------------------


        burgerReference = FirebaseDatabase.getInstance().getReference("Products").child("Burger");

          return view;

    }



    @Override
    public void onStart() {
        super.onStart();
        if (firebaseRecyclerAdapter != null) {
            firebaseRecyclerAdapter.startListening();
            mRecyclerView.setAdapter(firebaseRecyclerAdapter);
        }
    }


    private int getAdapterItemPosition(long id)
    {
        for (int position=0; position<item.size(); position++)
            if (item.get(position).getCategory_id() == id)
                return position;
        return 0;
    }
    public void showData() {
        //  options ilə setQuery və Product qeyd edərək konfiqurasiya edirik
        options = new FirebaseRecyclerOptions.Builder<Product>().setQuery(databaseReference , Product.class).build();
        // Product ile ProductHolderi binding etmek uchun firebaseRecyclerAdapter istifade edirik
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Product, ProductViewHolder>(options) {

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

}