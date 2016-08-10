package com.demo.util;

import android.content.Context;
import android.provider.Settings;

import javax.inject.Inject;

/**
 * Created by Beka on 8/10/16.
 */
public class DeviceUtils  {
    private final Context mContext;

    @Inject
    public DeviceUtils(Context context) {
        mContext = context;
    }


    public String getDeviceId() {
        return Settings.Secure.getString(mContext.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }
}
