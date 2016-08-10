package com.demo;

import com.demo.data.DebugDataModule;
import com.demo.ui.DebugUiModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        addsTo = DemoappModule.class,
        includes = {
                DebugUiModule.class,
                DebugDataModule.class,
                DebugActionsModule.class
        },
        overrides = true
)
public final class DebugDemoAppModule {
    // Low-tech flag to force certain debug build behaviors when running in an instrumentation test.
    // This value is used in the creation of singletons so it must be set before the graph is created.
    static boolean instrumentationTest = false;

    @Provides
    @Singleton
    @IsInstrumentationTest
    boolean provideIsInstrumentationTest() {
        return instrumentationTest;
    }
}
