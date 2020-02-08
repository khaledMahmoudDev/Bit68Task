package com.example.bit68market.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bit68market.R;
import com.example.bit68market.adapter.CategoryAdapter;
import com.example.bit68market.adapter.ImageSliderAdapter;
import com.example.bit68market.data_reposatory.DataStatus;
import com.example.bit68market.model.Category;
import com.example.bit68market.viewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements DataStatus {

    private ViewPager viewPager;
    private LinearLayout dotslinear;
    ImageSliderAdapter sliderAdapter;
    private int currentPage;
    private TextView[] dots;


    private MainViewModel mainViewModel;
    private ProgressBar progressBar;
    RecyclerView catedoryRecylerView;
    CategoryAdapter categoryAdapter;
    Toolbar toolbar;
    Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!isNetworkAvailable())
        {
            LinearLayout linearLayout = findViewById(R.id.linear_main_activity);
            linearLayout.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Please check Network", Toast.LENGTH_LONG).show();
        }

        toolbar = findViewById(R.id.toolbar_mainActivity);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.imgSwitcher);
        dotslinear = findViewById(R.id.linearDotsMain);
        progressBar = findViewById(R.id.progress_bar_mainActivity);


        progressBar.setVisibility(View.VISIBLE);
        catedoryRecylerView = findViewById(R.id.recycler_MainActivity);

        catedoryRecylerView.setLayoutManager(new GridLayoutManager(this, 2));
        catedoryRecylerView.setHasFixedSize(true);

        categoryAdapter = new CategoryAdapter();


        addDotsIndicator(0);
        currentPage = 0;


        final List<Category> list = new ArrayList<>();


        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getListCategory(this).observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {

                categoryAdapter.setCategoryList(categories);

                sliderAdapter = new ImageSliderAdapter(getBaseContext(), categories);
                viewPager.setAdapter(sliderAdapter);
                createSlideShow();


            }
        });

        categoryAdapter.setCategoryItemClickListener(new CategoryAdapter.CategoryItemClickListener() {
            @Override
            public void onCategoryItemClickListener(Category category) {
                Intent intent = new Intent(getBaseContext(), ProductsActivity.class);
                intent.putExtra("category", category);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onSucess() {
        progressBar.setVisibility(View.INVISIBLE);
        catedoryRecylerView.setAdapter(categoryAdapter);


    }

    @Override
    public void onFailure() {
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(this, "Please make sure you have acess to the network",Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menutwo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_cart_id) {
            Toast.makeText(this, "My Cart", Toast.LENGTH_SHORT).show();
        }
        return true;

    }


    void createSlideShow() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                if (currentPage == dots.length) {

                    currentPage = 0;
                }

                addDotsIndicator(currentPage);
                viewPager.setCurrentItem(currentPage++, true);

            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 250, 2500);

    }


    public void addDotsIndicator(int position) {
        dots = new TextView[4];
        dotslinear.removeAllViews();


        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.transparentWhite));
            dotslinear.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
