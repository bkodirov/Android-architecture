package com.demo.presenter.base;

/**
 * Created by Beka on 8/4/16.
 */
public class Error {
    private int mErrorCode;
    private int mPrimaryMessage;
    private int mSecondaryMessage;

    public int getErrorCode() {
        return mErrorCode;
    }

    public int getPrimaryMessage() {
        return mPrimaryMessage;
    }

    public int getSecondaryMessage() {
        return mSecondaryMessage;
    }

    @Override
    public String toString() {
        return "Error{" +
                "mErrorCode=" + mErrorCode +
                ", mPrimaryMessage=" + mPrimaryMessage +
                ", mSecondaryMessage=" + mSecondaryMessage +
                '}';
    }
}
