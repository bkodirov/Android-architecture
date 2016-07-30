package com.demo;

import com.demo.ui.InternalReleaseUiModule;

import dagger.Module;

@Module(
    addsTo = U2020Module.class,
    includes = InternalReleaseUiModule.class,
    overrides = true
)
public final class InternalReleaseDemoAppModule {
}
