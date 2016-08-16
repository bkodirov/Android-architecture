package com.demo.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Beka on 8/11/16.
 */
public class ResponseWrapper<T> {
    @SerializedName("IsSuccess")
    private int IsSuccess;
    @SerializedName("Data")
    private T data;

    public boolean isSuccess() {
        return IsSuccess > 0;
    }

    public T getData() {
        return data;
    }
}
