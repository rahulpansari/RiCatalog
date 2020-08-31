package com.ricatalog.ricatalog.network;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Products {
    @SerializedName("data")
    private List<ProductsData> productsdata;

    public List<ProductsData> getProductsdata() {
        return productsdata;
    }

    public class ProductsData
    {
        @SerializedName("id")
        private String id;
        @SerializedName("title")
        private String title;
        @SerializedName("code")
        private String code;
        @SerializedName("pid")
        private String pid;
        @SerializedName("cimg")
        private String cimg;

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getCode() {
            return code;
        }

        public String getPid() {
            return pid;
        }

        public String getCimg() {
            return cimg;
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            return super.equals(obj);
        }
    }
}
