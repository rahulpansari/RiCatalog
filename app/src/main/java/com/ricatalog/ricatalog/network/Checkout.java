package com.ricatalog.ricatalog.network;

import com.google.gson.annotations.SerializedName;

public class Checkout {
    @SerializedName("success")
    private String success;

    public String getSuccess() {
        return success;
    }
}
