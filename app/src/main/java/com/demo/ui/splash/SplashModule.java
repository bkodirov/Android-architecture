package com.demo.ui.splash;


import com.demo.presenter.modules.splash.SplashPresenter;
import com.demo.presenter.modules.splash.SplashPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                SplashActivity.class,
        },

        complete = false,
        library = true
)
public final class SplashModule {
    @Provides
    SplashPresenter provideViewContainer(SplashPresenterImpl splashPresenter) {
        return splashPresenter;
    }


}
