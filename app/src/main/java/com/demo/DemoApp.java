package com.demo;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.demo.data.LumberYard;
import com.demo.ui.base.ActivityHierarchyServer;
import com.demo.ui.rx_activity_result.RxActivityResult;
import com.google.firebase.iid.FirebaseInstanceId;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import dagger.ObjectGraph;
import io.fabric.sdk.android.Fabric;
import no.noname.baseapp.BuildConfig;
import timber.log.Timber;

import static timber.log.Timber.DebugTree;

public final class DemoApp extends Application {
    private ObjectGraph objectGraph;
    private static DemoApp sDemoApp;

    @Inject ActivityHierarchyServer activityHierarchyServer;
    @Inject LumberYard lumberYard;

    @Override
    public void onCreate() {
        super.onCreate();
        sDemoApp = this;
        AndroidThreeTen.init(this);
        RxActivityResult.register(this);
        LeakCanary.install(this);

        Fabric.with(this, new Crashlytics());
        if (BuildConfig.DEBUG) {
            Timber.plant(new DebugTree());
        } else {
            // TODO Crashlytics.start(this);
            // TODO Timber.plant(new CrashlyticsTree());
        }

        objectGraph = ObjectGraph.create(Modules.list(this));
        objectGraph.inject(this);

        lumberYard.cleanUp();

        FlowManager.init(new FlowConfig.Builder(this).openDatabasesOnInit(true).build());
        FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);

        Timber.plant(lumberYard.tree());

        registerActivityLifecycleCallbacks(activityHierarchyServer);
        String token = FirebaseInstanceId.getInstance().getToken();
        Timber.d("Token: %s", token);
    }

    public static Context getContext() {
        return sDemoApp;
    }

    public static ObjectGraph obtain() {
        return sDemoApp.objectGraph;
    }
}
