package com.demo.data.model.auth;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Beka on 8/10/16.
 */
public class SendSmsResponse {
    @SerializedName("IsSuccess")
    private int IsSuccess;
    private SmsSendResponseData Data;

    public boolean isSuccess() {
        return IsSuccess > 0;
    }

    public SmsSendResponseData getData() {
        return Data;
    }
}
