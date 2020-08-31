package com.ricatalog.ricatalog.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetail {
    @SerializedName("data")
    private List<ProductDetailList> getdatalist;

    public List<ProductDetailList> getGetdatalist() {
        return getdatalist;
    }

    public class ProductDetailList
    {
        @SerializedName("id")
        private String id;
        @SerializedName("title")
        private String title;
        @SerializedName("code")
        private String code;
        @SerializedName("cimg")
        private String image;
        @SerializedName("qty")
        private String qty;
        @SerializedName("color")
        private String color;
        @SerializedName("polish")
        private String polish;

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getCode() {
            return code;
        }

        public String getImage() {
            return image;
        }

        public String getQty() {
            return qty;
        }

        public String getColor() {
            return color;
        }

        public String getPolish() {
            return polish;
        }
    }
}
