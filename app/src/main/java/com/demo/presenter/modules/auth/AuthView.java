package com.demo.presenter.modules.auth;

import com.demo.domain.entity.Country;
import com.demo.domain.entity.Language;
import com.demo.presenter.base.MvpView;
import com.demo.ui.auth.AuthFragment;
import com.demo.ui.rx_activity_result.Result;
import com.tbruyelle.rxpermissions.Permission;

import java.util.List;

import rx.Observable;

/**
 * Created by Beka on 8/8/16.
 */
public interface AuthView extends MvpView {
    void setLanguages(List<Language> language);
    void setCountry(Country selectedCountry, List<Country> countryList);
    void openSmsVerifier(String phoneNumber,Language language);
    Observable<Result<AuthFragment>> openCountrySelector();
    Observable<Permission> checkNumberPermissions();
}
