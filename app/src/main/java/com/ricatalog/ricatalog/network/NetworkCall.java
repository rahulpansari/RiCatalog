package com.ricatalog.ricatalog.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkCall {
    public static Retrofit getRetrofit()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitApi.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       return retrofit;
    }
}
