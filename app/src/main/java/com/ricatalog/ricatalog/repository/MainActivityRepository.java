package com.ricatalog.ricatalog.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ricatalog.ricatalog.network.Category;
import com.ricatalog.ricatalog.network.Login;
import com.ricatalog.ricatalog.network.NetworkCall;
import com.ricatalog.ricatalog.network.RetrofitApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityRepository {
    private MutableLiveData<Category> getCategory;
    public static MainActivityRepository getInstance()
    {
        return new MainActivityRepository();
    }
    public MainActivityRepository()
    {
        getCategory=new MutableLiveData<>();
    }
    public void getGetCategories()
    {
        Call<Category> call= NetworkCall.getRetrofit().create(RetrofitApi.class).getCategories();
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                getCategory.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                getCategory.postValue(null);
            }
        });
    }

    public  MutableLiveData<Category> getCategoryList()
    {
        return  getCategory;
    }
}
