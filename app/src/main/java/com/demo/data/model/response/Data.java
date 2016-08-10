package com.demo.data.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Beka on 8/8/16.
 */
public class Data {
    @SerializedName("Message")
    private String message;
    @SerializedName("RequestId")
    private String requestId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
