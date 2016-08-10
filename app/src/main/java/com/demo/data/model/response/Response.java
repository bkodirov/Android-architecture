package com.demo.data.model.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Beka on 8/8/16.
 */
public class Response {
    @SerializedName("IsSuccess")
    private boolean isSuccess;
    @SerializedName("Data")
    private Data data;

    public boolean isSuccess() {
        return isSuccess;
    }

    public Data getData() {
        return data;
    }
}
