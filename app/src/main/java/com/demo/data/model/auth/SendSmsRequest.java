package com.demo.data.model.auth;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Beka on 8/8/16.
 */
public class SendSmsRequest {

    @SerializedName("NativeLanguage")
    private int nativeLanguage;
    @SerializedName("PhoneNumber")
    private String phoneNumber;
    @SerializedName("CountryCode")
    private String countryCode;

    public int getNativeLanguage() {
        return nativeLanguage;
    }

    public void setNativeLanguage(int nativeLanguage) {
        this.nativeLanguage = nativeLanguage;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
