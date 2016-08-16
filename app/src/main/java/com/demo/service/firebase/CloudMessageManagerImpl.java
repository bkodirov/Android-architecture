package com.demo.service.firebase;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import javax.inject.Inject;

/**
 * Created by Beka on 8/9/16.
 */

public class CloudMessageManagerImpl implements  CloudMessageManager {

    private static final String TOPIC = "news";

    @Inject
    public CloudMessageManagerImpl() {
    }

    public void init() {
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC);
    }

    public void logOut() {

    }

    public String getCloudMessageClientId() {
        return FirebaseInstanceId.getInstance().getToken();
    }

}
