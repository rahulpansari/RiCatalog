package com.ricatalog.ricatalog.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductCount {
    @SerializedName("data")
    private List<ProductList> data;

    public class ProductList
    {
        @SerializedName("pcount")
        private Integer pcount;

        public Integer getPcount() {
            return pcount;
        }
    }

    public List<ProductList> getData() {
        return data;
    }
}
