package com.example.bit68market.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bit68market.R;
import com.example.bit68market.adapter.SliderAdapter;

public class OnBoardingActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout dotslinear;
    private SliderAdapter sliderAdapter;
    private TextView[] dots;
    private Button previous;
    private Button next;

    private int currentPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (restorePrefData()) {
            Intent mainActivity = new Intent(this, MainActivity.class);
            startActivity(mainActivity);
            finish();
        }
        setContentView(R.layout.activity_on_boarding);


        viewPager = findViewById(R.id.viewPager);
        dotslinear = findViewById(R.id.linearDots);
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        previous = findViewById(R.id.btn_previous);
        next = findViewById(R.id.btn_next);


        addDotsIndicator(0);

        viewPager.addOnPageChangeListener(onPageChangeListener);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentPage == dots.length - 1) {
                    startActivity(new Intent(getBaseContext(), MainActivity.class));

                    savePrefsData();
                    finish();
                }
                viewPager.setCurrentItem(currentPage + 1);

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                viewPager.setCurrentItem(currentPage - 1);

            }
        });


    }


    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDotsIndicator(position);
            currentPage = position;

            if (position == 0) {
                previous.setEnabled(false);
                next.setEnabled(true);
                previous.setVisibility(View.INVISIBLE);

                next.setText("Next");
                previous.setText("");

            } else if (position == dots.length - 1) {
                previous.setEnabled(true);
                next.setEnabled(true);
                previous.setVisibility(View.VISIBLE);

                next.setText("Finish");
                previous.setText("Back");
            } else {
                previous.setEnabled(true);
                next.setEnabled(true);
                previous.setVisibility(View.VISIBLE);

                next.setText("Next");
                previous.setText("back");

            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    public void addDotsIndicator(int position) {
        dots = new TextView[3];
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

    private boolean restorePrefData() {


        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpenend", false);
        return isIntroActivityOpnendBefore;
    }


    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpenend", true);
        editor.commit();

    }
}
