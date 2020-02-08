package com.example.bit68market.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bit68market.data_reposatory.DataStatus;
import com.example.bit68market.data_reposatory.Reposatory;
import com.example.bit68market.model.Category;

import java.util.List;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<Category>> listCategory;

    public MutableLiveData<List<Category>> getListCategory(DataStatus status) {
        Reposatory reposatory = new Reposatory(status);

        listCategory = reposatory.getAllCategories();

        return listCategory;
    }


}
