package com.example.commercial;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class Adapter extends FirebaseRecyclerAdapter<Product , Adapter.ProductViewHolder> {



    public Adapter(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Product model) {
        holder.title.setText(model.getTitle());
        Picasso.get().load(model.getImage()).into(holder.img);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_grid_layout , viewGroup , false);
        return new ProductViewHolder(view);

    }

    public  class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView img;
         public ProductViewHolder(@NonNull View itemView) {
             super(itemView);
             title = itemView.findViewById(R.id.burger_name);
             img = itemView.findViewById(R.id.burger_img);
         }
}



   
}
