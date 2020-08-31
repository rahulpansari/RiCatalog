package com.ricatalog.ricatalog.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CartDao {

    @Insert
    void insert(CartEntity entity);

    @Update
    void update(CartEntity entity);

    @Delete
    void delete(CartEntity entity);

    @Query("SELECT * FROM cart_products")
    public LiveData<List<CartEntity>> getEntities();


    @Query("SELECT COUNT(*) FROM cart_products")
    public LiveData<Integer> getCount();

    @Query("DELETE FROM cart_products")
    public void delete();
}