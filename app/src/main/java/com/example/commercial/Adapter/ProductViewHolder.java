package com.example.commercial.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commercial.MainFragment.HomeFragment;
import com.example.commercial.R;
import com.squareup.picasso.Picasso;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    View mview;


    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
               mview = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              mClickListener.onItemClick(view , getBindingAdapterPosition());
            }
        });
         itemView.setOnLongClickListener(new View.OnLongClickListener() {
             @Override
             public boolean onLongClick(View view) {
                 mClickListener.onItemLongClick(view , getBindingAdapterPosition());
                 return false;
             }
         });
    }
     public void setProductDetails( String title , String image) {
         TextView mTitle = mview.findViewById(R.id.burger_name);
             ImageView mImage = mview.findViewById(R.id.burger_img);

         mTitle.setText(title);

         Picasso.get().load(image).into(mImage);
     }
      private ProductViewHolder.ClickListener mClickListener;

    public interface ClickListener {
        void  onItemClick (View view , int position);
        void  onItemLongClick (View view , int position);

    }
     public void setOnClickListener (ProductViewHolder.ClickListener clickListener) {
        mClickListener = clickListener;
     }

}
