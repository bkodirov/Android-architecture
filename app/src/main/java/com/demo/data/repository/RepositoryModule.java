package com.demo.data.repository;

import com.demo.data.repository.content.CommonContentRepositoryImpl;
import com.demo.data.repository.languages.LanguagesRepositoryImpl;
import com.demo.domain.repository.CommonContentRepository;
import com.demo.domain.repository.LanguagesRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Beka on 8/5/16.
 */

@Module(
        complete = false,
        library = true
)
public class RepositoryModule {

    @Provides
    LanguagesRepository provideLanguagesRepository(LanguagesRepositoryImpl languagesRepositoryImpl){
        return languagesRepositoryImpl;
    }

    @Provides
    CommonContentRepository provideCommonContentRepository(CommonContentRepositoryImpl repository){
        return repository;
    }
}
