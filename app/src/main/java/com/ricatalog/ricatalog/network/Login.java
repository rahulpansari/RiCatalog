package com.ricatalog.ricatalog.network;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Login {
    @SerializedName("success")
    private String code;

    @SerializedName("data")
    private ArrayList<User> data;

    public class User
    {
        @SerializedName("id")
        private String id;

        @SerializedName("o_phone")
        private String phoneno;

        @SerializedName("fname")
        private String fname;

        public String getId() {
            return id;
        }

        public String getPhoneno() {
            return phoneno;
        }

        public String getFname() {
            return fname;
        }
    }
    public String getCode() {
        return code;
    }

    public ArrayList<User> getData() {
        return data;
    }

    public Login(String code)
    {
        this.code=code;
    }
}
