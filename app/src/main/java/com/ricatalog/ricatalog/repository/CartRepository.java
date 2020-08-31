package com.ricatalog.ricatalog.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Entity;

import com.ricatalog.ricatalog.database.CartDao;
import com.ricatalog.ricatalog.database.CartEntity;
import com.ricatalog.ricatalog.database.RIDatabase;
import com.ricatalog.ricatalog.network.Checkout;
import com.ricatalog.ricatalog.network.NetworkCall;
import com.ricatalog.ricatalog.network.Registeration;
import com.ricatalog.ricatalog.network.RetrofitApi;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartRepository {
    private CartDao cartDao;
private MutableLiveData<Checkout> getCheckout;
    Context application;
    public CartRepository(Context application) {
        this.application=application;
        RIDatabase database=RIDatabase.getInstance(application);
        this.cartDao = database.getCartDao();
        getCheckout=new MutableLiveData<>();



    }
    public void insert(CartEntity entity)
    {
        new InsertEntity(cartDao).execute(entity);
    }
    public MutableLiveData<Checkout> getGetCheckoutLiveData()
    {
        return  getCheckout;
    }
    public void update(CartEntity entity)
    {
        new UpdateEntity(cartDao).execute(entity);
    }
    public void delete(CartEntity entity)
    {
        new DeleteEntity(cartDao).execute(entity);
    }
    public void deleteAll(){new DeleteEntities(cartDao).execute();}
    public LiveData<List<CartEntity>> getRecycleList()
    {
        return cartDao.getEntities();
    }
    public LiveData<Integer> getTotalCount()
    {
        return cartDao.getCount();
    }
    public static class InsertEntity extends AsyncTask<CartEntity,Void,Void>
    {   CartDao dao;
        @Override
        protected Void doInBackground(CartEntity... recyclerEntities) {
            dao.insert(recyclerEntities[0]);
            return null;
        }

        public InsertEntity(CartDao recyclerDao) {
            super();
            dao=recyclerDao;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    public static class UpdateEntity extends AsyncTask<CartEntity,Void,Void>
    {   CartDao dao;
        @Override
        protected Void doInBackground(CartEntity... recyclerEntities) {
            dao.update(recyclerEntities[0]);
            return null;
        }

        public UpdateEntity(CartDao recyclerDao) {
            super();
            dao=recyclerDao;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    public static class DeleteEntity extends AsyncTask<CartEntity,Void,Void>
    {   CartDao dao;
        @Override
        protected Void doInBackground(CartEntity... recyclerEntities) {
            dao.delete(recyclerEntities[0]);
            return null;
        }

        public DeleteEntity(CartDao recyclerDao) {
            super();
            dao=recyclerDao;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
    public static class DeleteEntities extends AsyncTask<Void,Void,Void>
    {   CartDao dao;
        @Override
        protected Void doInBackground(Void... recyclerEntities) {
            dao.delete();
            return null;
        }

        public DeleteEntities(CartDao recyclerDao) {
            super();
            dao=recyclerDao;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
    public void checkoutCart(Map<String,String> map)
    {
        Call<Checkout> call= NetworkCall.getRetrofit().create(RetrofitApi.class).checkoutCart(map.get("pid"),map.get("code"),map.get("qty"),map.get("price"),map.get("polish"),map.get("color"),map.get("id"));
        call.enqueue(new Callback<Checkout>() {
            @Override
            public void onResponse(Call<Checkout> call, Response<Checkout> response) {
                getCheckout.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Checkout> call, Throwable t) {
                getCheckout.postValue(null);
            }
        });
    }
}
