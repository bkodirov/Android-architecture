package com.demo.ui.trending;

import android.view.View;

import com.demo.ui.debug.ContextualDebugActions;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class ScrollBottomTrendingDebugAction extends ContextualDebugActions.DebugAction {

    @Inject
    public ScrollBottomTrendingDebugAction() {
        super(null);

    }

    @Override
    public String name() {
        return "Scroll to bottom";
    }

    @Override
    public void run(View view) {

    }
}
