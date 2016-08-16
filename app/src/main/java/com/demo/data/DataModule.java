package com.demo.data;

import android.app.Application;
import android.content.SharedPreferences;

import com.demo.data.executor.JobExecutor;
import com.demo.data.net.ApiModule;
import com.demo.data.net.RestApiInterceptor;
import com.demo.data.repository.RepositoryModule;
import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.byteunits.DecimalByteUnit;

import org.threeten.bp.Clock;

import java.io.File;
import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

import static android.content.Context.MODE_PRIVATE;

@Module(
        includes = {ApiModule.class, RepositoryModule.class},
        complete = false,
        library = true
)
public final class DataModule {
    public static final int DISK_CACHE_SIZE = (int) DecimalByteUnit.MEGABYTES.toBytes(50);

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Application app) {
        return app.getSharedPreferences("chatlas", MODE_PRIVATE);
    }

    @Provides
    @Singleton
    RxSharedPreferences provideRxSharedPreferences(SharedPreferences prefs) {
        return RxSharedPreferences.create(prefs);
    }


    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().setDateFormat("MM/dd/yyyy HH:mm:ss ZZZZZ").create();
    }

    @Provides
    @Singleton
    Clock provideClock() {
        return Clock.systemDefaultZone();
    }


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Application app, RestApiInterceptor restApiInterceptor) {
        return createOkHttpClient(app).addInterceptor(restApiInterceptor).build();
    }


    @Provides
    @Singleton
    Executor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    public static OkHttpClient.Builder createOkHttpClient(Application app) {
        // Install an HTTP cache in the application cache directory.
        File cacheDir = new File(app.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);

        return new OkHttpClient.Builder().cache(cache);
    }
}
