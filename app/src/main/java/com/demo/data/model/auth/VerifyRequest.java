package com.demo.data.model.auth;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Beka on 8/8/16.
 */
public class VerifyRequest {

    @SerializedName("PhoneNumber")
    private String PhoneNumber;
    @SerializedName("PushToken")
    private String PushToken;
    @SerializedName("RequestId")
    private String RequestId;
    @SerializedName("DeviceId")
    private String DeviceId;
    @SerializedName("SmsCode")
    private String SmsCode;

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getPushToken() {
        return PushToken;
    }

    public String getRequestId() {
        return RequestId;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public String getSmsCode() {
        return SmsCode;
    }
}
