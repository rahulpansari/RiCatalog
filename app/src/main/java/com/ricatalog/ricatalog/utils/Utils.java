package com.ricatalog.ricatalog.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.ricatalog.ricatalog.R;

import static android.content.Context.MODE_PRIVATE;

public class Utils {
    public static void setImage(ImageView imgview, Context c,String path) {
        String paths="http://radiantimpexjewels.com/erp/app/"+path;
        Glide.with(c).load(paths).placeholder(R.drawable.loading).into(imgview);
    }
    public static void setNewImage(ImageView imgview, Context c,String path) {
        String paths="http://radiantimpexjewels.com/erp/small/"+path;
        Glide.with(c).load(paths).placeholder(R.drawable.loading).into(imgview);
    }

    public static void  showSuccesfulToast(Context c, Activity activity,String text)
    {
        LayoutInflater inflater=activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.error_toast,(ViewGroup) activity.findViewById(R.id.heasdviewgrp));
       CardView cardView= layout.findViewById(R.id.toast_card);
       cardView.setCardBackgroundColor(c.getResources().getColor(R.color.greencolor));
        TextView textView=layout.findViewById(R.id.toast_text);
        textView.setText(text);
        Toast toast = new Toast(c);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);//setting the view of custom toast layout
        toast.show();
    }
    public static void  showErrorToast(Context c, Activity activity,String text)
    {
        LayoutInflater inflater=activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.error_toast,(ViewGroup) activity.findViewById(R.id.heasdviewgrp));
        CardView cardView= layout.findViewById(R.id.toast_card);
        cardView.setCardBackgroundColor(c.getResources().getColor(R.color.redcolor));
        TextView textView=layout.findViewById(R.id.toast_text);
        textView.setText(text);
        Toast toast = new Toast(c);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);//setting the view of custom toast layout
        toast.show();
    }

    public static SharedPreferences.Editor getShraredPreferencesEditor(Context activity)
    {
        SharedPreferences preferences=activity.getSharedPreferences("spreferences",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        return  editor;
    }

    public static SharedPreferences getShraredPreferences(Context activity)
    {
        SharedPreferences preferences=activity.getSharedPreferences("spreferences",MODE_PRIVATE);
        return  preferences;
    }
}
