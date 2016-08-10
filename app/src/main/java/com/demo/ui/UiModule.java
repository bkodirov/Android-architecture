package com.demo.ui;

import com.demo.ui.auth.AuthModule;
import com.demo.ui.main.MainActivity;
import com.demo.ui.splash.SplashModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = {SplashModule.class, AuthModule.class},
        injects = {
                MainActivity.class
        },
        complete = false,
        library = true
)
public final class UiModule {
    @Provides
    @Singleton
    ViewContainer provideViewContainer() {
        return ViewContainer.DEFAULT;
    }

    @Provides
    @Singleton
    ActivityHierarchyServer provideActivityHierarchyServer() {
        return ActivityHierarchyServer.NONE;
    }
}
