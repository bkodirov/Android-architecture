package com.demo.data;

import android.app.Application;
import android.content.SharedPreferences;

import com.demo.data.api.DebugApiModule;
import com.demo.data.net.RestApiInterceptor;
import com.demo.data.prefs.InetSocketAddressPreferenceAdapter;
import com.f2prateek.rx.preferences.Preference;
import com.f2prateek.rx.preferences.RxSharedPreferences;

import java.net.InetSocketAddress;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.inject.Singleton;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module(
        includes = DebugApiModule.class,
        complete = false,
        library = true,
        overrides = true
)
public final class DebugDataModule {


    @Provides
    @Singleton
    RxSharedPreferences provideRxSharedPreferences(SharedPreferences prefs) {
        return RxSharedPreferences.create(prefs);
    }


    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Application app,
                                     Preference<InetSocketAddress> networkProxyAddress,
                                     HttpLoggingInterceptor loggingInterceptor,
                                     RestApiInterceptor restApiInterceptor) {
        return DataModule.createOkHttpClient(app)
                .sslSocketFactory(createBadSslSocketFactory())
                .addInterceptor(loggingInterceptor)
                .addInterceptor(restApiInterceptor)
                .proxy(InetSocketAddressPreferenceAdapter.createProxy(networkProxyAddress.get()))
                .build();
    }
    @Provides
    @Singleton
    @ApiEndpoint
    Preference<String> provideEndpointPreference(RxSharedPreferences preferences) {
        return preferences.getString("debug_endpoint", ApiEndpoints.STAGING.url);
    }

    @Provides
    @Singleton
    @IsMockMode
    boolean provideIsMockMode(@ApiEndpoint Preference<String> endpoint) {
        // Running in an instrumentation forces mock mode.
//        return isInstrumentationTest || ApiEndpoints.isMockMode(endpoint.get());
        return false;
    }

    @Provides
    @Singleton
    @NetworkDelay
    Preference<Long> provideNetworkDelay(RxSharedPreferences preferences) {
        return preferences.getLong("debug_network_delay", 2000l);
    }

    @Provides
    @Singleton
    @NetworkFailurePercent
    Preference<Integer> provideNetworkFailurePercent(RxSharedPreferences preferences) {
        return preferences.getInteger("debug_network_failure_percent", 3);
    }

    @Provides
    @Singleton
    @NetworkVariancePercent
    Preference<Integer> provideNetworkVariancePercent(RxSharedPreferences preferences) {
        return preferences.getInteger("debug_network_variance_percent", 40);
    }

    @Provides
    @Singleton
    Preference<InetSocketAddress> provideNetworkProxyAddress(RxSharedPreferences preferences) {
        return preferences.getObject("debug_network_proxy",
                InetSocketAddressPreferenceAdapter.INSTANCE);
    }


    private static SSLSocketFactory createBadSslSocketFactory() {
        try {
            // Construct SSLSocketFactory that accepts any cert.
            SSLContext context = SSLContext.getInstance("TLS");
            TrustManager permissive = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };
            context.init(null, new TrustManager[]{permissive}, null);
            return context.getSocketFactory();
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }
}
