package com.demo.ui;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
    injects = {
        MainActivity.class,
    },
    complete = false,
    library = true
)
public final class UiModule {
  @Provides @Singleton ViewContainer provideViewContainer() {
    return ViewContainer.DEFAULT;
  }

  @Provides @Singleton ActivityHierarchyServer provideActivityHierarchyServer() {
    return ActivityHierarchyServer.NONE;
  }
}
