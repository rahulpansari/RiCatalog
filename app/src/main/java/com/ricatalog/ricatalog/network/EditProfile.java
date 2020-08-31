package com.ricatalog.ricatalog.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EditProfile {
    @SerializedName("data")
    private List<ProfileDetails> data;

    public List<ProfileDetails> getData() {
        return data;
    }

    public class ProfileDetails
    {
        @SerializedName("id")
        private String id;
        @SerializedName("username")
        private String username;
        @SerializedName("fname")
        private String fname;
        @SerializedName("email")
        private String email;
        @SerializedName("company_name")
        private String company_name;
        @SerializedName("gst_no")
        private String gst_no;
        @SerializedName("address")
        private String address;
        @SerializedName("code")
        private String code;
        @SerializedName("city")
        private String city;
        @SerializedName("state")
        private String state;
        @SerializedName("o_phone")
        private String o_phone;
        @SerializedName("password")
        private String password;
        @SerializedName("country")
        private String country;

        public String getCountry() {
            return country;
        }

        public String getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getFname() {
            return fname;
        }

        public String getEmail() {
            return email;
        }

        public String getCompany_name() {
            return company_name;
        }

        public String getGst_no() {
            return gst_no;
        }

        public String getAddress() {
            return address;
        }

        public String getCode() {
            return code;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public String getO_phone() {
            return o_phone;
        }

        public String getPassword() {
            return password;
        }
    }
}
