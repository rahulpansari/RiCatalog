package com.ricatalog.ricatalog.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ricatalog.ricatalog.network.Category;
import com.ricatalog.ricatalog.network.Login;
import com.ricatalog.ricatalog.network.NetworkCall;
import com.ricatalog.ricatalog.network.ProductCount;
import com.ricatalog.ricatalog.network.Products;
import com.ricatalog.ricatalog.network.RetrofitApi;
import com.ricatalog.ricatalog.network.SubCategory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivityRepository {
    private MutableLiveData<ProductCount> getCount;
    public static ProductActivityRepository getInstance()
    {
        return new ProductActivityRepository();
    }
    public ProductActivityRepository()
    {
        getCount=new MutableLiveData<>();

    }

    public void fetchTotalProducts(String subid)
    {
        Call<ProductCount> call= NetworkCall.getRetrofit().create(RetrofitApi.class).getProductCount(subid);
        call.enqueue(new Callback<ProductCount>() {
            @Override
            public void onResponse(Call<ProductCount> call, Response<ProductCount> response) {
                getCount.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ProductCount> call, Throwable t) {
                getCount.postValue(null);
            }
        });
    }


    public  MutableLiveData<ProductCount> getProductCountNo()
    {
        return  getCount;
    }


}
