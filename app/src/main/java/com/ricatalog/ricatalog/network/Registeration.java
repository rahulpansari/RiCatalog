package com.ricatalog.ricatalog.network;

import com.google.gson.annotations.SerializedName;
public class Registeration {
    @SerializedName("success")
    private String code;

    public String getCode() {
        return code;
    }

    public Registeration(String code) {
        this.code = code;
    }
}
