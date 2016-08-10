package com.demo.domain.repository;

import com.demo.domain.entity.User;

import rx.Observable;

/**
 * Created by Beka on 8/10/16.
 */
public interface AuthRepository {
    Observable<String> getSms();

    Observable<User> registerUser(String clientId, String confirmationNumber, String phoneNumber, String pushToken, String deviceId);
}
