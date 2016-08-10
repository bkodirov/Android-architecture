package com.demo.presenter.modules.main;


import com.demo.managers.AccountManager;

import javax.inject.Inject;

public class MainPresenterImpl extends MainPresenter {

    private final AccountManager mAccountManager;

    @Inject
    public MainPresenterImpl(AccountManager accountManager) {
        mAccountManager = accountManager;
    }

    @Override
    public void onViewAttached() {
        getView().initUi();
    }

    @Override
    public void checkAuthorization() throws NotAuthorizedException {

    }
}
