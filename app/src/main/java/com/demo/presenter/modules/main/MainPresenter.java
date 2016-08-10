package com.demo.presenter.modules.main;

import com.demo.presenter.base.Presenter;

/**
 * Created by Beka on 8/4/16.
 */
public abstract class MainPresenter extends Presenter<MainView> {

    abstract public void checkAuthorization() throws NotAuthorizedException;
}
