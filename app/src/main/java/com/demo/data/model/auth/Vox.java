package com.demo.data.model.auth;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Beka on 8/16/16.
 */
public class Vox implements Serializable{
    @SerializedName("UserId")
    private String userId;
    @SerializedName("UserName")
    private String userName;
    @SerializedName("DisplayName")
    private String displayName;
    @SerializedName("Password")
    private String password;

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Vox{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", displayName='" + displayName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
