package com.example.bit68market.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bit68market.R;
import com.example.bit68market.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<Category> categoryList = new ArrayList<>();
    private CategoryItemClickListener categoryItemClickListener;

    public void setCategoryItemClickListener(CategoryItemClickListener categoryItemClickListener) {
        this.categoryItemClickListener = categoryItemClickListener;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_element, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);

        Glide.with(holder.categoryImage.getContext()).load(category.getCategory_img()).into(holder.categoryImage);
        holder.categoryText.setText(category.getName());


    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        ImageView categoryImage;
        TextView categoryText;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryImage = itemView.findViewById(R.id.img_category_list_element);
            categoryText = itemView.findViewById(R.id.txt_category_list_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (categoryItemClickListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        categoryItemClickListener.onCategoryItemClickListener(categoryList.get(getAdapterPosition()));
                    }
                }
            });


        }
    }
    public interface CategoryItemClickListener {

        public void onCategoryItemClickListener(Category category);
    }
}


