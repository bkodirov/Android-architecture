package com.demo.data.model.auth;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Beka on 8/11/16.
 */
public class TwilioResponse {
    @SerializedName("country_code")
    private String countryCode;

    public String getCountryCode() {
        return countryCode;
    }
}
