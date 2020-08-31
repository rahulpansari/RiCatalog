package com.ricatalog.ricatalog.network;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category implements  Parcelable  {

    @SerializedName("data")
    private List<CategoryList> categoryList;

    protected Category(Parcel in) {

    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public List<CategoryList> getCategoryList() {
        return categoryList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public class  CategoryList
    {

        @SerializedName("catid")
        private String catid;
        @SerializedName("category")
        private String category;
        @SerializedName("imgpath")
        private String imgpath;

        public String getCatid() {
            return catid;
        }

        public String getCategory() {
            return category;
        }

        public String getImgpath() {
            return imgpath;
        }
    }
}
