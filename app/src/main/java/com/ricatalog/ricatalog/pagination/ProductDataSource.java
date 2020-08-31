package com.ricatalog.ricatalog.pagination;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.ricatalog.ricatalog.activity.ProductActivity;
import com.ricatalog.ricatalog.network.NetworkCall;
import com.ricatalog.ricatalog.network.Products;
import com.ricatalog.ricatalog.network.RetrofitApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDataSource extends PageKeyedDataSource<String, Products.ProductsData> {
    public static String PAGE_SIZE="10";
    private Integer OFFSET;
    private int limit1=0,limit2=0;
    public MutableLiveData<Boolean> initialValue;
    public  MutableLiveData<Boolean> loadLiveData;

    public ProductDataSource()
    {
        initialValue=new MutableLiveData<>();
        loadLiveData=new MutableLiveData<>();

    }

    public MutableLiveData getInitialValue()
    {
        return initialValue;
    }

    public MutableLiveData getLoadValue()
    {
        return loadLiveData;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull final LoadInitialCallback<String, Products.ProductsData> callback) {
        limit2=ProductActivity.COUNT>10?10:ProductActivity.COUNT;
        Call<Products> call= NetworkCall.getRetrofit().create(RetrofitApi.class).getProducts(ProductActivity.sid,String.valueOf(limit1),String.valueOf(limit2));
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                OFFSET=ProductActivity.COUNT>10?11:null;
                callback.onResult(response.body().getProductsdata(), null, String.valueOf(OFFSET));
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Products.ProductsData> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull final LoadCallback<String, Products.ProductsData> callback) {
        loadLiveData.postValue(true);
        if(OFFSET!=null) {
            limit1=OFFSET;
            limit2=limit1+9<ProductActivity.COUNT?limit1+9:ProductActivity.COUNT;
            Call<Products> call= NetworkCall.getRetrofit().create(RetrofitApi.class).getProducts(ProductActivity.sid,String.valueOf(limit1),String.valueOf(limit2));
            call.enqueue(new Callback<Products>() {
                @Override
                public void onResponse(Call<Products> call, Response<Products> response) {
                    loadLiveData.postValue(false);
                    OFFSET = limit2+1<ProductActivity.COUNT?limit2+1:null;
                    callback.onResult(response.body().getProductsdata(),  String.valueOf(OFFSET));
                }

                @Override
                public void onFailure(Call<Products> call, Throwable t) {
                }
            });
        }
    }

}
