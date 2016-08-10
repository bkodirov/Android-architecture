package com.demo.presenter.modules.auth;

import com.demo.domain.entity.Country;
import com.demo.domain.entity.Language;
import com.demo.presenter.base.MvpView;

import java.util.List;

/**
 * Created by Beka on 8/8/16.
 */
public interface AuthView extends MvpView {
    void showLoading();
    void hideLoading();
    void setLanguages(List<Language> language);
    void setCountries(Country countrie);
    void openSmsVerifier();
    void openCountrySelector();
}
