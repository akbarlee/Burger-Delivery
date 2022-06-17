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

import org.w3c.dom.Text;

public class Adapter extends FirebaseRecyclerAdapter<Product , Adapter.viewHolder>{


    public Adapter(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull Product model) {
        holder.textView.setText(model.getTitle());

       Picasso.get().load(model.getImage()).into(holder.imageView);
    }



@NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main , parent , false);
       return  new viewHolder(view);
    }

    class viewHolder extends  RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.burger_img);
            textView = (TextView) itemView.findViewById(R.id.burger_name);
        }
    }
}
