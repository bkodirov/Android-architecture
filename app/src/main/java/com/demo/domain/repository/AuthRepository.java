package com.demo.domain.repository;

import com.demo.data.model.auth.SendSmsRequest;
import com.demo.domain.entity.Country;
import com.demo.domain.entity.User;

import java.util.List;

import rx.Observable;

/**
 * Created by Beka on 8/10/16.
 */
public interface AuthRepository {
    Observable<List<Country>> getCountries();

    Observable<String> getSms(SendSmsRequest request);

    Observable<User> registerUser(String clientId, String confirmationNumber, String phoneNumber, String pushToken, String deviceId);
}
