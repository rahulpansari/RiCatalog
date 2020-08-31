package com.ricatalog.ricatalog.viewmodel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.ricatalog.ricatalog.repository.LoginActivityRepository;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ZoomActivityViewModel  extends ViewModel {
    private MutableLiveData<Bitmap> bitmapimg;
    public ZoomActivityViewModel() {
        super();
        bitmapimg=new MutableLiveData<>();
    }
    public MutableLiveData<Bitmap> getBitmap()
    {
        return bitmapimg;
    }
    public void fetchBitmap(String img)
    {
        new LoadBitmap().execute(img);
    }
    class LoadBitmap extends AsyncTask<String, Void,Bitmap>
    {
        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bm = null;
            InputStream is = null;
            BufferedInputStream bis = null;
            URLConnection conn;
            try
            {
                conn = new URL(strings[0]).openConnection();
                conn.connect();
                is = conn.getInputStream();
                bis = new BufferedInputStream(is);
                bm = BitmapFactory.decodeStream(bis);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally {
                if (bis != null)
                {
                    try
                    {
                        bis.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                if (is != null)
                {
                    try
                    {
                        is.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            return bm;
        }

        public LoadBitmap() {
            super();
        }

        @Override
        protected void onPostExecute(Bitmap aVoid) {
            super.onPostExecute(aVoid);
            bitmapimg.postValue(aVoid);
            }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            }
    }
}
