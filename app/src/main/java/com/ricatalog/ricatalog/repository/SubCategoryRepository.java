package com.ricatalog.ricatalog.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ricatalog.ricatalog.network.Category;
import com.ricatalog.ricatalog.network.Login;
import com.ricatalog.ricatalog.network.NetworkCall;
import com.ricatalog.ricatalog.network.RetrofitApi;
import com.ricatalog.ricatalog.network.SubCategory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoryRepository {
    private MutableLiveData<SubCategory> getSubCategory;
    public static SubCategoryRepository getInstance()
    {
        return new SubCategoryRepository();
    }
    public SubCategoryRepository()
    {
        getSubCategory=new MutableLiveData<>();
    }
    public void getGetSubCategories(String categoryid)
    {
        Call<SubCategory> call= NetworkCall.getRetrofit().create(RetrofitApi.class).getSubCategories(categoryid);
        call.enqueue(new Callback<SubCategory>() {
            @Override
            public void onResponse(Call<SubCategory> call, Response<SubCategory> response) {
                getSubCategory.postValue(response.body());
            }

            @Override
            public void onFailure(Call<SubCategory> call, Throwable t) {
                getSubCategory.postValue(null);
            }
        });
    }

    public  MutableLiveData<SubCategory> getCategoryList()
    {
        return  getSubCategory;
    }
}
