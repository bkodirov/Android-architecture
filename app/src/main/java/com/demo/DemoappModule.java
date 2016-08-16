package com.demo;

import android.app.Application;

import com.demo.data.DataModule;
import com.demo.service.ServiceModule;
import com.demo.ui.UiModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = {
                UiModule.class,
                DataModule.class,
                ServiceModule.class
        },
        injects = {
                DemoApp.class
        }
)
public final class DemoappModule {
    private final DemoApp app;

    public DemoappModule(DemoApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }

}
