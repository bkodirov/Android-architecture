package com.demo.service.firebase;

import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Beka on 8/9/16.
 */
public interface CloudMessageCallback {
    void onTokenRefresh(String token);

    void onRegister(String  token);

    void onMessageRecieved(RemoteMessage remoteMessage);
}
