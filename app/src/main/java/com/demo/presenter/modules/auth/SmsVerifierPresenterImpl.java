package com.demo.presenter.modules.auth;

import android.os.CountDownTimer;
import android.util.Log;

import com.demo.data.model.auth.SendSmsRequest;
import com.demo.domain.entity.Language;
import com.demo.domain.entity.User;
import com.demo.domain.repository.AuthRepository;
import com.demo.managers.AccountManager;
import com.demo.service.firebase.CloudMessageManager;
import com.demo.service.firebase.CloudMessageManagerImpl;
import com.demo.util.DeviceUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import rx.Subscriber;
import timber.log.Timber;

/**
 * Created by Beka on 8/15/16.
 */
public class SmsVerifierPresenterImpl extends SmsVerifierPresenter {
    private final AuthRepository mAuthRepository;
    private final CloudMessageManager mCloudMessageManager;
    private final DeviceUtils mDeviceUtils;
    private final AccountManager mAccountManager;
    private CountDownTimer countDownTimer;
    private String requestId;
    private String mPhoneNumber;

    @Inject
    public SmsVerifierPresenterImpl(AuthRepository authRepository,
                                    CloudMessageManagerImpl cloudMessageManager,
                                    DeviceUtils deviceUtils,
                                    AccountManager accountManager) {
        this.mAuthRepository = authRepository;
        this.mCloudMessageManager = cloudMessageManager;
        this.mDeviceUtils = deviceUtils;
        this.mAccountManager = accountManager;
    }

    @Override
    public void init(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    @Override
    public void verifyCode(String code) {
        getView().showLoading();
        String pushId = mCloudMessageManager.getCloudMessageClientId();
        mAuthRepository.registerUser(requestId, code, mPhoneNumber, pushId, mDeviceUtils.getDeviceId()).subscribe(new Subscriber<User>() {
            @Override
            public void onCompleted() {
                Timber.d("Complete");
                getView().hideLoading();
                getView().openMainWindow();
            }

            @Override
            public void onError(Throwable e) {
                Timber.e("error %s", Log.getStackTraceString(e));
                getView().hideLoading();
                getView().showError(e.getMessage());
            }

            @Override
            public void onNext(User user) {
                Timber.d("onNext %s", user);
                mAccountManager.storeAccount(user);
            }
        });
    }

    @Override
    public void getSms(String phoneNumber, Language language) {
        SendSmsRequest request = new SendSmsRequest();
        request.setPhoneNumber(phoneNumber);
        request.setNativeLanguage(language.getValue());
        request.setCountryCode(phoneNumber);
        mAuthRepository.getSms(request).subscribe(s -> {
            Timber.d("RequestID %s", s);
            this.requestId = s;
            getView().hideLoading();
        }, throwable -> {
            getView().showError("Something went wrong");
            getView().hideLoading();
        });
        initCountDownTimer();
    }

    @Override
    public void onViewAttached() {
        initCountDownTimer();
    }

    private void initCountDownTimer() {
        countDownTimer = new CountDownTimer(180_000, 1000) {
            SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

            public void onTick(long millisUntilFinished) {
                getView().updateTimerView(sdf.format(new Date(millisUntilFinished)));
            }

            public void onFinish() {
                getView().waitTimeIsUp();
            }
        }.start();
    }

    @Override
    public void detachView() {
        super.detachView();
        countDownTimer.cancel();
    }
}


