package com.example.bit68market.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {


    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    public RetrofitClient() {

        retrofit  = new Retrofit.Builder()
                .baseUrl("https://5bcce576cf2e850013874767.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static RetrofitClient getmInstance() {
        if (mInstance == null)
        {
            mInstance = new RetrofitClient();

        }
        return mInstance;
    }

    public NetworkApi getApi()
    {
        return retrofit.create(NetworkApi.class);
    }
}
