package com.data.api;

import com.data.ApiEndpoint;
import com.data.IsMockMode;
import com.data.NetworkDelay;
import com.data.NetworkFailurePercent;
import com.data.NetworkVariancePercent;
import com.data.net.GithubService;
import com.f2prateek.rx.preferences.Preference;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;
import timber.log.Timber;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Module(
        complete = false,
        library = true,
        overrides = true
)
public final class DebugApiModule {
    @Provides
    @Singleton
    HttpUrl provideHttpUrl(@ApiEndpoint Preference<String> apiEndpoint) {
        return HttpUrl.parse(apiEndpoint.get());
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> Timber.tag("OkHttp").v(message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        return loggingInterceptor;
    }

    @Provides
    @Singleton
    NetworkBehavior provideBehavior(@NetworkDelay Preference<Long> networkDelay,
                                    @NetworkFailurePercent Preference<Integer> networkFailurePercent,
                                    @NetworkVariancePercent Preference<Integer> networkVariancePercent) {
        NetworkBehavior behavior = NetworkBehavior.create();
        behavior.setDelay(networkDelay.get(), MILLISECONDS);
        behavior.setFailurePercent(networkFailurePercent.get());
        behavior.setVariancePercent(networkVariancePercent.get());
        return behavior;
    }

    @Provides
    @Singleton
    MockRetrofit provideMockRetrofit(Retrofit retrofit,
                                     NetworkBehavior behavior) {
        return new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();
    }

    @Provides
    @Singleton
    GithubService provideGithubService(Retrofit retrofit,
                                       @IsMockMode boolean isMockMode) {
        return retrofit.create(GithubService.class);
    }
}
