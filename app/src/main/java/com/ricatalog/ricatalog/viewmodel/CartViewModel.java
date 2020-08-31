package com.ricatalog.ricatalog.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ricatalog.ricatalog.database.CartEntity;
import com.ricatalog.ricatalog.network.Checkout;
import com.ricatalog.ricatalog.repository.CartRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class CartViewModel extends AndroidViewModel {
    CartRepository recyclerRepository;

    public CartViewModel(@NotNull Application application)
    {super(application);
        recyclerRepository=new CartRepository(getApplication());
    }

    public LiveData<List<CartEntity>> getEntity()
    {
        return recyclerRepository.getRecycleList();
    }
    public LiveData<Integer> getEntityCount()
    {
        return recyclerRepository.getTotalCount();
    }
    public void insertProduct(CartEntity entity)
    {
        recyclerRepository.insert(entity);
    }
    public void updateProduct(CartEntity entity)
    {
        recyclerRepository.update(entity);
    }
    public void removeProduct(CartEntity entity)
    {
        recyclerRepository.delete(entity);
    }
    public void removeAll()
    {
        recyclerRepository.deleteAll();
    }
    public MutableLiveData<Checkout> getCheckoutLiveData()
    {
        return recyclerRepository.getGetCheckoutLiveData();
    }
    public void fetchCheckout(Map<String,String> map)
    {
        recyclerRepository.checkoutCart(map);
    }
}
