package com.example.bit68market.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.bit68market.R;
import com.example.bit68market.model.Category;

import java.util.List;

public class ImageSliderAdapter extends PagerAdapter {

    Context context;
    List<Category> categorieList;


    LayoutInflater layoutInflater;

    public ImageSliderAdapter(Context context, List<Category> categorieList) {
        this.context = context;
        this.categorieList = categorieList;
    }



    @Override
    public int getCount() {
        return categorieList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.image_slider_element, container, false);


        ImageView imageView = view.findViewById(R.id.img_slider);
        Glide.with(context).load(categorieList.get(position).getCategory_img()).into(imageView);
        container.addView(view);

        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);

    }
}
