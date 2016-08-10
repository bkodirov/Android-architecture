package com.demo.service.firebase;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import timber.log.Timber;

/**
 * Created by Beka on 8/9/16.
 */
public class IdService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        Timber.d(FirebaseInstanceId.getInstance().getToken());
    }
}
