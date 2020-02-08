package com.example.bit68market.data_reposatory;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.bit68market.model.Category;
import com.example.bit68market.network.RetrofitClient;

import java.util.List;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Reposatory {
    private DataStatus dataStatus;

    private static final String TAG = "Reposatory";

    private MutableLiveData<List<Category>> allCategories = new MutableLiveData<>();

    public MutableLiveData<List<Category>> getAllCategories() {
        return allCategories;
    }

    public Reposatory(DataStatus status) {
        dataStatus = status;

        Single<List<Category>> single = RetrofitClient.getmInstance().getApi().getCategories()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        single.subscribe(new SingleObserver<List<Category>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<Category> categories) {

                allCategories.setValue(categories);
                dataStatus.onSucess();

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "get categories" + e);
                dataStatus.onFailure();

            }
        });
    }


}
