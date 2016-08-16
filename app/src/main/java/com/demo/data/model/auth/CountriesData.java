package com.demo.data.model.auth;

import com.demo.domain.entity.Country;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Beka on 8/11/16.
 */
public class CountriesData {
    @SerializedName("Countries")
    private List<Country> mCountries;

    public List<Country> getCountries() {
        return mCountries;
    }
}
