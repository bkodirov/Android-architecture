package com.demo;

import android.app.Application;

import com.data.DataModule;
import com.demo.ui.UiModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
    includes = {
        UiModule.class,
        DataModule.class
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

  @Provides @Singleton Application provideApplication() {
    return app;
  }
}
