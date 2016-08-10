package com.demo.ui.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.demo.presenter.modules.splash.SplashPresenter;
import com.demo.presenter.modules.splash.SplashView;
import com.demo.ui.BaseActivity;
import com.demo.ui.auth.AuthActivity;

import javax.inject.Inject;

import no.noname.baseapp.R;

/**
 * Created by Beka on 8/3/16.
 */
public class SplashActivity extends BaseActivity implements SplashView {
    @Inject SplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        finish();
        startActivity(AuthActivity.getIntent(this));
    }
}
