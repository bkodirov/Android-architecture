package com.demo.presenter.modules.auth;

import com.demo.presenter.base.MvpView;

/**
 * Created by Beka on 8/15/16.
 */
public interface SmsVerifierView extends MvpView{
    void updateTimerView(String text);
    void waitTimeIsUp();
    void openMainWindow();
}
