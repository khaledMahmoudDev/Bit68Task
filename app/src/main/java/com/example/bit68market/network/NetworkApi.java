package com.example.bit68market.network;

import com.example.bit68market.model.Category;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface NetworkApi {

    @GET("/task/categories")
    Single<List<Category>> getCategories();

}
