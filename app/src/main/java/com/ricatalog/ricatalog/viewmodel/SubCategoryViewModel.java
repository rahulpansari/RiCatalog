package com.ricatalog.ricatalog.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ricatalog.ricatalog.network.Category;
import com.ricatalog.ricatalog.network.SubCategory;
import com.ricatalog.ricatalog.repository.MainActivityRepository;
import com.ricatalog.ricatalog.repository.SubCategoryRepository;

public class SubCategoryViewModel extends ViewModel {
    SubCategoryRepository subCategoryRepository;
    public SubCategoryViewModel() {
        super();
        subCategoryRepository=SubCategoryRepository.getInstance();
    }
    public MutableLiveData<SubCategory> getSubCategoryList()
    {
        return  subCategoryRepository.getCategoryList();
    }

    public void getSubCategories(String categoryid)
    {
        subCategoryRepository.getGetSubCategories(categoryid);
    }

}
