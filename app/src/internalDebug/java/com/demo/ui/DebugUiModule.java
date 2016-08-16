package com.demo.ui;

import android.app.Application;

import com.demo.IsInstrumentationTest;
import com.demo.data.IsMockMode;
import com.demo.data.MockRequestHandler;
import com.demo.data.PicassoDebugging;
import com.demo.data.PixelGridEnabled;
import com.demo.data.PixelRatioEnabled;
import com.demo.data.ScalpelEnabled;
import com.demo.data.ScalpelWireframeEnabled;
import com.demo.data.SeenDebugDrawer;
import com.demo.ui.base.ActivityHierarchyServer;
import com.demo.ui.debug.DebugView;
import com.demo.ui.debug.DebugViewContainer;
import com.demo.ui.debug.SocketActivityHierarchyServer;
import com.f2prateek.rx.preferences.Preference;
import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.mock.NetworkBehavior;
import timber.log.Timber;

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
    private static final int DEFAULT_ANIMATION_SPEED = 1; // 1x (normal) speed.
    private static final boolean DEFAULT_PICASSO_DEBUGGING = false; // Debug indicators displayed
    private static final boolean DEFAULT_PIXEL_GRID_ENABLED = false; // No pixel grid overlay.
    private static final boolean DEFAULT_PIXEL_RATIO_ENABLED = false; // No pixel ratio overlay.
    private static final boolean DEFAULT_SCALPEL_ENABLED = false; // No crazy 3D view tree.
    private static final boolean DEFAULT_SCALPEL_WIREFRAME_ENABLED = false; // Draw views by default.
    private static final boolean DEFAULT_SEEN_DEBUG_DRAWER = false; // Show debug drawer first time.

    @Provides
    @Singleton
    ViewContainer provideViewContainer(DebugViewContainer debugViewContainer,
                                       @IsInstrumentationTest boolean isInstrumentationTest) {
        // Do not add the debug controls for when we are running inside of an instrumentation test.
        return isInstrumentationTest ? ViewContainer.DEFAULT : debugViewContainer;
    }


    @Provides
    @Singleton
    ActivityHierarchyServer provideActivityHierarchyServer() {
        return new SocketActivityHierarchyServer();
    }

    @Provides
    @Singleton
    @Named("AnimationSpeed")
    Preference<Integer> provideAnimationSpeed(RxSharedPreferences preferences) {
        return preferences.getInteger("debug_animation_speed", DEFAULT_ANIMATION_SPEED);
    }

    @Provides
    @Singleton
    @PicassoDebugging
    Preference<Boolean> providePicassoDebugging(RxSharedPreferences preferences) {
        return preferences.getBoolean("debug_picasso_debugging", DEFAULT_PICASSO_DEBUGGING);
    }

    @Provides
    @Singleton
    @PixelGridEnabled
    Preference<Boolean> providePixelGridEnabled(RxSharedPreferences preferences) {
        return preferences.getBoolean("debug_pixel_grid_enabled", DEFAULT_PIXEL_GRID_ENABLED);
    }

    @Provides
    @Singleton
    @PixelRatioEnabled
    Preference<Boolean> providePixelRatioEnabled(RxSharedPreferences preferences) {
        return preferences.getBoolean("debug_pixel_ratio_enabled", DEFAULT_PIXEL_RATIO_ENABLED);
    }

    @Provides
    @Singleton
    @SeenDebugDrawer
    Preference<Boolean> provideSeenDebugDrawer(RxSharedPreferences preferences) {
        return preferences.getBoolean("debug_seen_debug_drawer", DEFAULT_SEEN_DEBUG_DRAWER);
    }

    @Provides
    @Singleton
    @ScalpelEnabled
    Preference<Boolean> provideScalpelEnabled(RxSharedPreferences preferences) {
        return preferences.getBoolean("debug_scalpel_enabled", DEFAULT_SCALPEL_ENABLED);
    }

    @Provides
    @Singleton
    @ScalpelWireframeEnabled
    Preference<Boolean> provideScalpelWireframeEnabled(RxSharedPreferences preferences) {
        return preferences.getBoolean("debug_scalpel_wireframe_drawer",
                DEFAULT_SCALPEL_WIREFRAME_ENABLED);
    }

    @Provides
    @Singleton
    Picasso providePicasso(OkHttpClient client, NetworkBehavior behavior,
                           @IsMockMode boolean isMockMode, Application app) {
        Picasso.Builder builder = new Picasso.Builder(app).downloader(new OkHttp3Downloader(client));
        if (isMockMode) {
            builder.addRequestHandler(new MockRequestHandler(behavior, app.getAssets()));
        }
        builder.listener((picasso, uri, exception) -> {
            Timber.e(exception, "Error while loading image %s", uri);
        });
        return builder.build();
    }
}
