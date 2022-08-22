package com.example.commercial.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commercial.Model.Category;
import com.example.commercial.R;

import java.util.ArrayList;

public class CategoryViewAdapter extends RecyclerView.Adapter<CategoryViewAdapter.CategoryViewHolder> {
    private ArrayList<Category> items;

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
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
      Category currentItem = items.get(position);
      holder.imageView.setImageResource(currentItem.getCategory_image());
      holder.textView.setText(currentItem.getCategory_title());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    ImageView imageView;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.category_img);
            textView = itemView.findViewById(R.id.category_title);
        }
    }

}
