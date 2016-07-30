package com.demo;

import android.app.Application;
import android.support.annotation.NonNull;

import com.demo.data.Injector;
import com.demo.data.LumberYard;
import com.demo.ui.ActivityHierarchyServer;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

import dagger.ObjectGraph;
import no.noname.baseapp.BuildConfig;
import timber.log.Timber;

import static timber.log.Timber.DebugTree;

public final class DemoApp extends Application {
  private ObjectGraph objectGraph;

  @Inject ActivityHierarchyServer activityHierarchyServer;
  @Inject LumberYard lumberYard;

  @Override public void onCreate() {
    super.onCreate();
    AndroidThreeTen.init(this);
    LeakCanary.install(this);

    if (BuildConfig.DEBUG) {
      Timber.plant(new DebugTree());
    } else {
      // TODO Crashlytics.start(this);
      // TODO Timber.plant(new CrashlyticsTree());
    }

    objectGraph = ObjectGraph.create(Modules.list(this));
    objectGraph.inject(this);

    lumberYard.cleanUp();
    Timber.plant(lumberYard.tree());

    registerActivityLifecycleCallbacks(activityHierarchyServer);
  }

  @Override public Object getSystemService(@NonNull String name) {
    if (Injector.matchesService(name)) {
      return objectGraph;
    }
    return super.getSystemService(name);
  }
}
