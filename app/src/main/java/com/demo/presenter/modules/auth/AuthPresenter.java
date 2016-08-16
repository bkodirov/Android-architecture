package com.demo.presenter.modules.auth;

import com.demo.domain.entity.Language;
import com.demo.presenter.base.Presenter;

/**
 * Created by Beka on 8/8/16.
 */
public abstract class AuthPresenter extends Presenter<AuthView> {
    public abstract void fetchCountries();

    public abstract void done(String phoneNumber, Language language);
}
