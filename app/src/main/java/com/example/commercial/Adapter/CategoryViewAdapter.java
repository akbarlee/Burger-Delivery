package com.example.commercial.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commercial.MainActivity;
import com.example.commercial.MainFragment.HomeFragment;
import com.example.commercial.Model.Category;
import com.example.commercial.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import java.util.ArrayList;

public class CategoryViewAdapter extends RecyclerView.Adapter<CategoryViewAdapter.CategoryViewHolder> {
    private ArrayList<Category> items;
    Category ctg;
    FirebaseRecyclerAdapter options;
    HomeFragment category;
    int row_index = -1;

    public CategoryViewAdapter(ArrayList<Category> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item , parent ,false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);
        return  categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder,  int position) {
       ctg = items.get(position);
       holder.imageView.setImageResource(ctg.getCategory_image());
        holder.textView.setText(ctg.getCategory_title());
        holder.itemView.setEnabled(false);
        holder.itemView.setEnabled(true);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   row_index = position;
                   notifyDataSetChanged(); // hər data dəyişdikdə viewi reflect edir
                switch(items.get(position).getCategory_id()){

                    case 0: /** Start a new Activity MyCards.java */

                        Log.i(String.valueOf(getItemViewType(0)), "MyClass.getView() — get item number " + position);
                        break;
                    case 1:
                        Query drinks = FirebaseDatabase.getInstance().getReference("Products")
                                .orderByChild("category")
                                .equalTo("Drinks");
                        FirebaseRecyclerOptions<Category> newOptions=
                                new FirebaseRecyclerOptions.Builder<Category>()
                                        .setQuery(drinks, Category.class)
                                        .build();

                        Log.i(String.valueOf(getItemViewType(1)), "MyClass.getView() — get item number " + position);
                             break;
                    case 4:

                        Log.i(String.valueOf(getItemViewType(1)), "MyClass.getView() — get item number " + position);
                        break;
                }
              //  Toast.makeText(holder.imageView.getContext(), "Hello" + position, Toast.LENGTH_SHORT).show();

          }
        });


        if( row_index == position) {

            holder.linearLayout.setBackgroundResource(R.drawable.category_background_selected);

        }
        else {
            holder.linearLayout.setBackgroundResource(R.drawable.category_background_simple);



        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    ImageView imageView;
    LinearLayout linearLayout;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.category_img);
            textView = itemView.findViewById(R.id.category_title);
            linearLayout = itemView.findViewById(R.id.category_item);
        }
    }

}
