package com.example.bit68market.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bit68market.R;
import com.example.bit68market.adapter.ProductAdapter;
import com.example.bit68market.model.Category;
import com.example.bit68market.model.Products;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProductsActivity extends AppCompatActivity {
    private Category category;
    private RecyclerView productsRecyclerView;
    private ProductAdapter productAdapter;
    ImageView categoryImage;
    Toolbar toolbar;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        category = (Category) getIntent().getSerializableExtra("category");

        toolbar = findViewById(R.id.toolbar_Category);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(category.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_backspace_black_24dp);

        bottomNavigationView = findViewById(R.id.bottom_navigation_home);


        categoryImage = findViewById(R.id.img_category_productActivity);
        Glide.with(this).load(category.getCategory_img()).into(categoryImage);


        productsRecyclerView = findViewById(R.id.recycler_products);
        productsRecyclerView.setHasFixedSize(true);
        productsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        productAdapter = new ProductAdapter();
        productAdapter.setProductList(category.getProducts());


        productsRecyclerView.setAdapter(productAdapter);

        productAdapter.setProductClickListener(new ProductAdapter.ProductClickListener() {
            @Override
            public void onProductClickListener(Products products) {

                Intent intent = new Intent(getBaseContext(), ProductDetailActivity.class);
                intent.putExtra("product", products);
                startActivity(intent);
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_filter:
                        Toast.makeText(ProductsActivity.this, "Filter", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_sortBy:
                        Toast.makeText(ProductsActivity.this, "Sort By", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menutwo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_cart_id:
                Toast.makeText(this, "My Cart", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }


    }



}
