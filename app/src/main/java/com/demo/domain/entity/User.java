package com.demo.domain.entity;


import com.demo.data.model.auth.Vox;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public final class User  implements Serializable{
    @SerializedName("UserId")
    private String userId;
    @SerializedName("FirstName")
    private String firstName;
    @SerializedName("LastName")
    private String lastName;
    @SerializedName("NativeLanguage")
    private String nativeLanguage;
    @SerializedName("Avatar")
    private String avatar;
    @SerializedName("Email")
    private String email;
    @SerializedName("PhoneNumber")
    private String phoneNumber;
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("expires_in")
    private long expiresIn;
    @SerializedName (".issued")
    private Date issued;
    @SerializedName (".expires")
    private Date expires;
    @SerializedName("VoxImplantUser")
    private Vox VoxImplantUser;

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNativeLanguage() {
        return nativeLanguage;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public Date  getIssued() {
        return issued;
    }

    public Date  getExpires() {
        return expires;
    }

    public Vox getVoxImplantUser() {
        return VoxImplantUser;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nativeLanguage='" + nativeLanguage + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", expiresIn=" + expiresIn +
                ", issued=" + issued +
                ", expires=" + expires +
                ", VoxImplantUser='" + VoxImplantUser + '\'' +
                '}';
    }
}
