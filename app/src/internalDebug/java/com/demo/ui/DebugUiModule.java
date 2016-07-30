package com.demo.ui;

import com.demo.IsInstrumentationTest;
import com.demo.ui.debug.DebugView;
import com.demo.ui.debug.DebugViewContainer;
import com.demo.ui.debug.SocketActivityHierarchyServer;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module(
    injects = {
        DebugViewContainer.class,
        DebugView.class,
    },
    complete = false,
    library = true,
    overrides = true
)
public class DebugUiModule {
  @Provides @Singleton ViewContainer provideViewContainer(DebugViewContainer debugViewContainer,
      @IsInstrumentationTest boolean isInstrumentationTest) {
    // Do not add the debug controls for when we are running inside of an instrumentation test.
    return isInstrumentationTest ? ViewContainer.DEFAULT : debugViewContainer;
  }

  @Provides @Singleton ActivityHierarchyServer provideActivityHierarchyServer() {
    return new SocketActivityHierarchyServer();
  }
}
