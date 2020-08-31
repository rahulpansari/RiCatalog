package com.ricatalog.ricatalog.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ricatalog.ricatalog.network.Category;
import com.ricatalog.ricatalog.repository.MainActivityRepository;

public class MainActivityViewModel extends ViewModel {
    MainActivityRepository mainActivityRepository;
    public MainActivityViewModel() {
        super();
        mainActivityRepository=MainActivityRepository.getInstance();
    }
    public MutableLiveData<Category> getCategoryList()
    {
        return  mainActivityRepository.getCategoryList();
    }

    public void getCategories()
    {
        mainActivityRepository.getGetCategories();
    }

}
