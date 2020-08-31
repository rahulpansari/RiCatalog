package com.ricatalog.ricatalog.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ricatalog.ricatalog.network.NetworkCall;
import com.ricatalog.ricatalog.network.ProductCount;
import com.ricatalog.ricatalog.network.ProductDetail;
import com.ricatalog.ricatalog.network.RetrofitApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailRepository {
    private MutableLiveData<com.ricatalog.ricatalog.network.ProductDetail> productdetails;
    public  static ProductDetailRepository getInstance()
    {
        return new ProductDetailRepository();
    }
    public  ProductDetailRepository()
    {
        productdetails=new MutableLiveData<>();
    }
    public void fetchProductDetail(String id)
    {
        Call<com.ricatalog.ricatalog.network.ProductDetail> call= NetworkCall.getRetrofit().create(RetrofitApi.class).getProductDetails(id);
        call.enqueue(new Callback<com.ricatalog.ricatalog.network.ProductDetail>() {
            @Override
            public void onResponse(Call<com.ricatalog.ricatalog.network.ProductDetail> call, Response<com.ricatalog.ricatalog.network.ProductDetail> response) {
                productdetails.postValue(response.body());
            }

            @Override
            public void onFailure(Call<com.ricatalog.ricatalog.network.ProductDetail> call, Throwable t) {
                productdetails.postValue(null);

            }
        });
    }
    public MutableLiveData<ProductDetail> getProductdetails()
    {
        return productdetails;
    }
}
