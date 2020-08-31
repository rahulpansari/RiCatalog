package com.ricatalog.ricatalog.network;

import com.google.gson.annotations.SerializedName;

public class ProfileUpdate {
    @SerializedName("success")
    private String success;

    public String getSuccess() {
        return success;
    }
}
