package com.ricatalog.ricatalog.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ricatalog.ricatalog.network.EditProfile;
import com.ricatalog.ricatalog.network.ProfileCountry;
import com.ricatalog.ricatalog.network.ProfileUpdate;
import com.ricatalog.ricatalog.repository.EditProfileRepository;

import java.util.Map;

public class EditProfileViewModel extends ViewModel {
    EditProfileRepository repository;
    private MutableLiveData<ProfileCountry.Country> countryMutableLiveData;
    public EditProfileViewModel() {
        super();
        repository=EditProfileRepository.getInstance();
        countryMutableLiveData=new MutableLiveData<>();

    }
    public MutableLiveData<EditProfile> getProfileList()
    {
        return repository.getUserProfile();
    }

    public MutableLiveData<ProfileCountry> getCountryList()
    {
        return repository.getCountrylistItem();
    }
    public void fetchCountryList()
    {
        repository.getCountryList();
    }
    public MutableLiveData<ProfileCountry.Country> getCountryMutableLiveData()
    {
        return countryMutableLiveData;
    }
   public void setCountry(ProfileCountry.Country c)
   {
       countryMutableLiveData.postValue(c);
   }
    public void fetchProfileList(String id)
    {
         repository.getUserProfileData(id);
    }
    public MutableLiveData<ProfileUpdate> getUpdateProfile(){return  repository.getProfileUpdate();}
    public void setProfileData(Map<String,String>map)
    {
        repository.setProfileData(map);
    }

}
