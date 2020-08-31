package com.ricatalog.ricatalog.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ricatalog.ricatalog.network.EditProfile;
import com.ricatalog.ricatalog.network.NetworkCall;
import com.ricatalog.ricatalog.network.ProfileCountry;
import com.ricatalog.ricatalog.network.ProfileUpdate;
import com.ricatalog.ricatalog.network.Registeration;
import com.ricatalog.ricatalog.network.RetrofitApi;
import com.ricatalog.ricatalog.utils.Utils;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileRepository {
    private MutableLiveData<EditProfile>getProfile;
    private MutableLiveData<ProfileUpdate> updateprofile;
    private MutableLiveData<ProfileCountry> countrylist;
    public static EditProfileRepository getInstance()
    {
        return new EditProfileRepository();
    }
    public EditProfileRepository()
    {
        getProfile=new MutableLiveData<>();
        updateprofile=new MutableLiveData<>();
        countrylist=new MutableLiveData<>();
    }
    public MutableLiveData<EditProfile> getUserProfile()
    {
        return getProfile;
    }
    public MutableLiveData<ProfileUpdate> getProfileUpdate()
    {
        return updateprofile;
    }

    public void getUserProfileData(String id)
    {
        Call<EditProfile> call= NetworkCall.getRetrofit().create(RetrofitApi.class).getProfile(id);
        call.enqueue(new Callback<EditProfile>() {
            @Override
            public void onResponse(Call<EditProfile> call, Response<EditProfile> response) {
                getProfile.postValue(response.body());
            }

            @Override
            public void onFailure(Call<EditProfile> call, Throwable t) {
                getProfile.postValue(null);
            }
        });
    }
    public void getCountryList()
    {
        Call<ProfileCountry> call= NetworkCall.getRetrofit().create(RetrofitApi.class).getCountryList();
        call.enqueue(new Callback<ProfileCountry>() {
            @Override
            public void onResponse(Call<ProfileCountry> call, Response<ProfileCountry> response) {
                countrylist.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ProfileCountry> call, Throwable t) {
                countrylist.postValue(null);
            }
        });
    }


    public void setProfileData(Map<String,String> map)
    {
        Call<ProfileUpdate> call= NetworkCall.getRetrofit().create(RetrofitApi.class).updateProfileDetails(map.get("id"),map.get("country"),map.get("password"),map.get("ophone"),map.get("state"),map.get("city"),map.get("code"),map.get("address"),map.get("companyname"),map.get("username"),map.get("mailid"),map.get("mobileno"),map.get("name"),map.get("gst_no"));
        call.enqueue(new Callback<ProfileUpdate>() {
            @Override
            public void onResponse(Call<ProfileUpdate> call, Response<ProfileUpdate> response) {
                updateprofile.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ProfileUpdate> call, Throwable t) {
                updateprofile.postValue(null);
            }
        });
    }

    public MutableLiveData<ProfileCountry> getCountrylistItem() {
        return countrylist;
    }
}
