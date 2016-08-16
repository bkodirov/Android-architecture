package com.demo.presenter.modules.auth;


import android.content.Context;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.demo.DemoApp;
import com.demo.domain.entity.Country;
import com.demo.domain.entity.Language;
import com.demo.domain.repository.AuthRepository;
import com.demo.domain.repository.LanguagesRepository;
import com.tbruyelle.rxpermissions.Permission;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import rx.Subscriber;
import timber.log.Timber;

/**
 * Created by Beka on 8/8/16.
 */
public class AuthPresenterImpl extends AuthPresenter {

    private final AuthRepository mAuthRepository;
    private final LanguagesRepository mLanguagesRepository;
    private List<Country> mCountries = new ArrayList<>();

    @Inject
    public AuthPresenterImpl(AuthRepository authRepository,
                             LanguagesRepository languagesRepository) {
        this.mAuthRepository = authRepository;
        this.mLanguagesRepository = languagesRepository;
    }

    @Override
    public void fetchCountries() {
        getView().showLoading();
        mAuthRepository.getCountries().subscribe(countries -> {
            mCountries.clear();
            mCountries.addAll(countries);
            Timber.d("Country list %s", mCountries);
            setProperCountry();
            getView().hideLoading();
        }, Throwable::printStackTrace, () -> getView().hideLoading());
    }

    private void setProperCountry() {
        getView().checkNumberPermissions().subscribe(new Subscriber<Permission>() {

            @Override
            public void onCompleted() {
                Timber.d("complete");
                String myNumber = getMyNumber();
                if (TextUtils.isEmpty(myNumber)) {
                    Timber.d("Empty number");
                    Country country = getCountry(Locale.getDefault().getCountry());
                    if (country == null && !mCountries.isEmpty()) country = mCountries.get(0);
                    getView().setCountry(country, mCountries);
                } else {
                    Country country = getCountry(myNumber);
                    if (country == null && !mCountries.isEmpty()) country = mCountries.get(0);
                    getView().setCountry(country, mCountries);
                }
            }

            @Override
            public void onError(Throwable e) {
                Timber.d("error");
                Country country = getCountry(Locale.getDefault().getCountry());
                getView().setCountry(country, mCountries);
            }

            @Override
            public void onNext(Permission permission) {
                Timber.d("next %s", permission);
            }
        });
    }


    @Override
    public void onViewAttached() {
        mLanguagesRepository.getLanguages(true).subscribe(languages -> getView().setLanguages(languages));
        fetchCountries();
    }

    @Override
    public void done(String phoneNumber, Language language) {
        getView().openSmsVerifier(phoneNumber, language);
    }

    private
    @Nullable
    String getMyNumber() {
        TelephonyManager tMgr = (TelephonyManager) DemoApp.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        return tMgr.getLine1Number();

    }

    private Country getCountry(String countryCode) {
        Timber.d("search country by code %s", countryCode);
        String cUpper = countryCode.toUpperCase();
        for (Country item : mCountries) {
            if (item.getCode() != null && item.getCode().toUpperCase().equals(cUpper)) return item;
        }
        return null;
    }
}
