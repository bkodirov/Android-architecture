package com.demo.ui.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.crashlytics.android.Crashlytics;
import com.demo.managers.AccountManager;
import com.demo.presenter.modules.splash.SplashPresenter;
import com.demo.presenter.modules.splash.SplashView;
import com.demo.ui.auth.AuthActivity;
import com.demo.ui.base.BaseActivity;
import com.demo.ui.main.MainActivity;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import io.fabric.sdk.android.Fabric;
import no.noname.baseapp.R;

/**
 * Created by Beka on 8/3/16.
 */
public class SplashActivity extends BaseActivity implements SplashView {
    @Inject SplashPresenter mSplashPresenter;
    @Inject AccountManager mAccountManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setCustomContentView(R.layout.activity_splash);
        mSplashPresenter.attachView(this);
        mSplashPresenter.synchPreloadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSplashPresenter.detachView();
    }

    @Override
    public void loadDone() {
        if (mAccountManager.isAuthorized()) {
            startActivity(MainActivity.getIntent(this));
        } else {
            startActivity(AuthActivity.getIntent(this));
        }
        finish();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }
}
