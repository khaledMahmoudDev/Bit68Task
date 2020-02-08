package com.example.bit68market.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.bit68market.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public int[] slide_images = {

            R.drawable.ic_boarding_one,
            R.drawable.ic_boarding_two,
            R.drawable.ic_boarding_three
    };
    public String[] slide_headings = {
            "Organic food",
            "Fresh food",
            "Fast Delivery"
    };

    public String[] slide_desc =
            {
                    "Organic food is the product of a farming system which avoids the use of man-made fertilisers, pesticides; growth regulators and livestock feed additives.",
                    "Let us choose only the freshest most nutritional vegetables, herbs and fruit when in season and bring it to you in a box every week. We only send you food when it\\'s in season, and only send you what is fresh.",
                    "Get products delivered to you the next day for a nominal fee in select markets. Just order by the cutoff time and we\\'ll bring your products to you -  so you can have it fresh."
            };


    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.on_boarding_element, container, false);


        ImageView imageView = view.findViewById(R.id.img_on_boarding);
        TextView heading = view.findViewById(R.id.txt_onBoarding_name);
        TextView detail = view.findViewById(R.id.txt_onBoarding_details);

        imageView.setImageResource(slide_images[position]);
        heading.setText(slide_headings[position]);
        detail.setText(slide_desc[position]);

        container.addView(view);

        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);

    }
}
