package com.demo.presenter.modules.auth;

import com.demo.presenter.base.Presenter;

/**
 * Created by Beka on 8/8/16.
 */
public abstract class AuthPresenter extends Presenter<AuthView> {
    public abstract void fetchCountries();
    public abstract void getSms();

    public abstract void selectCountry();
    public abstract void setCountry();

}
