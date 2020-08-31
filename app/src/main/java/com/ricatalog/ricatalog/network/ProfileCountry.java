package com.ricatalog.ricatalog.network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileCountry {
    @SerializedName("data")
    private List<Country> countryList;

    public class Country
    {
        @SerializedName("id")
        private String id;
        @SerializedName("country")
        private String country;

        public String getId() {
            return id;
        }

        public String getCountry() {
            return country;
        }
    }

    public List<Country> getCountryList() {
        return countryList;
    }
}
