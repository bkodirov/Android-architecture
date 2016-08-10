package com.demo.data.net;


import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(
        complete = false,
        library = true,
        injects = {
        }
)
public final class ApiModule {
    public static final HttpUrl PRODUCTION_API_URL = HttpUrl.parse("https://web.chatlasapp.com");

    @Provides
    @Singleton
    HttpUrl provideBaseUrl() {
        return PRODUCTION_API_URL;
    }


    @Provides
    @Singleton
    Retrofit provideRetrofit(HttpUrl baseUrl, OkHttpClient client,
                             Gson gson) {
        return new Retrofit.Builder() //
                .client(client) //
                .baseUrl(baseUrl) //
                .addConverterFactory(GsonConverterFactory.create(gson)) //
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //
                .build();
    }


    @Provides
    @Singleton
    LanguagesService provideLanguagesService(Retrofit retrofit) {
        return retrofit.create(LanguagesService.class);
    }

    @Provides
    @Singleton
    ContentService provideContentService(Retrofit retrofit){
        return retrofit.create(ContentService.class);
    }

}
