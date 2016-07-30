package com.demo.ui.trending;

import android.view.View;

import com.demo.ui.debug.ContextualDebugActions;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class ScrollTopTrendingDebugAction extends ContextualDebugActions.DebugAction {

    @Inject
    public ScrollTopTrendingDebugAction() {
        super(null);
    }

    @Override
    public String name() {
        return "Scroll to top";
    }

    @Override
    public void run(View view) {
    }
}
