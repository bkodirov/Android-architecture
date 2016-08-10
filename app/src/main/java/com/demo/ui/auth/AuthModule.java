package com.demo.ui.auth;

import com.demo.presenter.modules.auth.AuthPresenter;
import com.demo.presenter.modules.auth.AuthPresenterImpl;

import dagger.Module;
import dagger.Provides;


/**
 * Created by Beka on 8/8/16.
 */

@Module(
        complete = false,
        library = true,
        injects = {AuthActivity.class, AuthFragment.class})
public class AuthModule {
    @Provides
    AuthPresenter provideAuthPresenter(AuthPresenterImpl authPresenter) {
        return authPresenter;
    }
}
