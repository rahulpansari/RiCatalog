package com.ricatalog.ricatalog.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubCategory {
    @SerializedName("data")
    private List<SubCategoryList> subcategorylist;

    public List<SubCategoryList> getSubcategorylist() {
        return subcategorylist;
    }

    public class SubCategoryList
    {
        @SerializedName("catid")
        private String  subcatid;

        @SerializedName("category")
        private String subcategory;

        @SerializedName("imgpath")
        private String imgpath;

        public String getSubcatid() {
            return subcatid;
        }

        public String getSubcategory() {
            return subcategory;
        }

        public String getImgpath() {
            return imgpath;
        }
    }
}
