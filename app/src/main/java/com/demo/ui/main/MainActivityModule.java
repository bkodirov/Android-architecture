package com.demo.ui.main;

import dagger.Module;

@Module(
        injects = {
                SideMenuDrawerFragment.class,
                MainActivity.class
        },
        complete = false,
        library = true
)
public final class MainActivityModule {
}
