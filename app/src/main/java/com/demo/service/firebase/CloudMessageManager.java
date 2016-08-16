package com.demo.service.firebase;

/**
 * Created by Beka on 8/16/16.
 */
public interface CloudMessageManager {

    public void init();

    public void logOut();

    public String getCloudMessageClientId();
}
