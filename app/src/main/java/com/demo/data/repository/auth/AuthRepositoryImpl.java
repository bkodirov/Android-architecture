package com.demo.data.repository.auth;

import com.demo.data.model.ResponseWrapper;
import com.demo.data.model.auth.SendSmsRequest;
import com.demo.data.model.auth.VerifyRequest;
import com.demo.data.net.AuthService;
import com.demo.domain.entity.Country;
import com.demo.domain.entity.User;
import com.demo.domain.repository.AuthRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Beka on 8/15/16.
 */
public class AuthRepositoryImpl implements AuthRepository {

    private final AuthService mAuthService;

    @Inject
    public AuthRepositoryImpl(AuthService authService) {
        mAuthService = authService;
    }

    @Override
    public Observable<List<Country>> getCountries() {
        return mAuthService.getCountries().map(countriesDataResponseWrapper -> countriesDataResponseWrapper.getData().getCountries()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<String> getSms(SendSmsRequest request) {
        return mAuthService.register(request).map(response -> response.getData().getRequestId()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<User> registerUser(String clientId, String confirmationNumber, String phoneNumber, String pushToken, String deviceId) {
        VerifyRequest request=new VerifyRequest();
        request.setPhoneNumber(phoneNumber);
        request.setDeviceId(deviceId);
        request.setPushToken(pushToken);
        request.setRequestId(clientId);
        request.setSmsCode(confirmationNumber);
        return mAuthService.verifyCode(request).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(ResponseWrapper::getData);
    }
}
