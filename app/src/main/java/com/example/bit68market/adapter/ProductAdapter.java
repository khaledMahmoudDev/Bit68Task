package com.example.bit68market.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bit68market.R;
import com.example.bit68market.model.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private ProductClickListener productClickListener;
    private List<Products> productList;

    public void setProductClickListener(ProductClickListener productClickListener) {
        this.productClickListener = productClickListener;
    }

    public void setProductList(List<Products> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_element, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Products product = productList.get(position);
        Glide.with(holder.productImage.getContext()).load(product.getProduct_img()).into(holder.productImage);
        holder.productName.setText(product.getName());
        holder.productWeight.setText(product.getWeight());
        holder.productPrice.setText(product.getPrice());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        ImageView productCart;
        TextView productName;
        TextView productWeight;
        TextView productPrice;
        Boolean clicked;


        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);


            productImage = itemView.findViewById(R.id.img_product_element_image);
            productCart = itemView.findViewById(R.id.product_element_cart);
            productName = itemView.findViewById(R.id.txt_product_name);
            productWeight = itemView.findViewById(R.id.txt_product_weight);
            productPrice = itemView.findViewById(R.id.txt_product_price);
            clicked = false;

            productCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clicked) {
                        clicked = false;
                        productCart.setImageResource(R.drawable.add_24dp);
                        Toast.makeText(productCart.getContext(), "Removed From Cart", Toast.LENGTH_LONG).show();


                    } else {
                        clicked = true;
                        productCart.setImageResource(R.drawable.done_add_24dp);
                        Toast.makeText(productCart.getContext(), "Added To Cart", Toast.LENGTH_LONG).show();

                    }
                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (productClickListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        productClickListener.onProductClickListener(productList.get(getAdapterPosition()));
                    }
                }
            });
        }
    }
    public interface ProductClickListener {
        void onProductClickListener(Products products);
    }

}

