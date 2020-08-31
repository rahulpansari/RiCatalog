package com.ricatalog.ricatalog.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = CartEntity.class,version = 1,exportSchema = false)
public abstract class RIDatabase extends RoomDatabase {
    public static RIDatabase instance;
    public abstract CartDao getCartDao();

    public static synchronized  RIDatabase getInstance(Context context)
    {
        if(instance==null)
        {
            instance= Room.databaseBuilder(context.getApplicationContext(),RIDatabase.class,"ri-database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}