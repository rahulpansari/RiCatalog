package com.ricatalog.ricatalog.viewmodel;

import android.app.Activity;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ricatalog.ricatalog.network.Login;
import com.ricatalog.ricatalog.network.Registeration;
import com.ricatalog.ricatalog.repository.LoginActivityRepository;

import java.util.Map;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<Fragment> getFragment;
    LoginActivityRepository repository;
    public LoginViewModel() {
        super();
        getFragment=new MutableLiveData<>();
        repository=LoginActivityRepository.getInstance();
    }
    public void setFragment(Fragment frg)
    {
        getFragment.setValue(frg);
    }
    public void loginUser(Map<String,String> map)
    {
        repository.loginUser(map);
    }
    public void registerUser(Map<String,String> map)
    {
        repository.registerUser(map);
    }
    public MutableLiveData<Registeration> getGetRegisterationDetails()
    {
        return repository.getRegisterationdetails();
    }
    public MutableLiveData<Login> getLoginDetails()
    {
        return repository.getLogindetails();
    }
    public void setRegisterationDetails()
    {
        repository.setRegisterationdetails();
    }
    public void setLoginDetails()
    {
        repository.setLogindetails();
    }

}
