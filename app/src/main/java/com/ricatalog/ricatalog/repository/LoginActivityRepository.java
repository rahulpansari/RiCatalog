package com.ricatalog.ricatalog.repository;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.ricatalog.ricatalog.network.Login;
import com.ricatalog.ricatalog.network.NetworkCall;
import com.ricatalog.ricatalog.network.Registeration;
import com.ricatalog.ricatalog.network.RetrofitApi;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityRepository {
    private MutableLiveData<Registeration> registerationdetails;
    private MutableLiveData<Login> logindetails;
    public static LoginActivityRepository getInstance()
    {

        return new LoginActivityRepository();

    }
    public LoginActivityRepository()
    {
        registerationdetails=new MutableLiveData<>();
        logindetails=new MutableLiveData<>();
    }
    public void registerUser(Map<String,String> map)
    {
       Call<Registeration> call= NetworkCall.getRetrofit().create(RetrofitApi.class).registerUser(map.get("name"),map.get("mobile"),map.get("email"),map.get("place"),map.get("company"));
        call.enqueue(new Callback<Registeration>() {
            @Override
            public void onResponse(Call<Registeration> call, Response<Registeration> response) {
                registerationdetails.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Registeration> call, Throwable t) {
                registerationdetails.postValue(new Registeration("-1"));
            }
        });
    }
    public void loginUser(Map<String,String> map)
    {
        Call<Login> call= NetworkCall.getRetrofit().create(RetrofitApi.class).loginUser(map.get("mobile"),map.get("password"));
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                logindetails.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                logindetails.postValue(new Login("-1"));
            }
        });
    }

    public MutableLiveData<Registeration> getRegisterationdetails()
    {
        return registerationdetails;
    }
    public MutableLiveData<Login> getLogindetails()
    {
        return logindetails;
    }
    public void setRegisterationdetails()
    {
        registerationdetails.postValue(null);
    }
    public void setLogindetails()
    {
         logindetails.postValue(null);
    }

}
