package com.example.bit68market.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.bit68market.R;
import com.example.bit68market.model.Products;

public class ProductDetailActivity extends AppCompatActivity {

    Products product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        product = (Products) getIntent().getSerializableExtra("product");

        TextView productName = findViewById(R.id.txt_productDetails_name);
        productName.setText(product.getName());

    }
}
