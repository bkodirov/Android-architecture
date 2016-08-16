package com.demo.presenter.modules.auth;

import com.demo.domain.entity.Language;
import com.demo.presenter.base.Presenter;

/**
 * Created by Beka on 8/15/16.
 */
public abstract class SmsVerifierPresenter extends Presenter<SmsVerifierView>{
    public abstract void init(String phoneNumber);
    public abstract void verifyCode(String code);
    public abstract void getSms(String phoneNumber, Language language);
}
