package com.demo.data.model.auth;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Beka on 8/10/16.
 */
public class SmsSendResponseData {
    @SerializedName("Message")
    private String Message;
    @SerializedName("RequestId")
    private String RequestId;

    public String getMessage() {
        return Message;
    }

    public String getRequestId() {
        return RequestId;
    }
}
