package com.demo.data.repository.languages;

import com.demo.data.net.LanguagesService;
import com.demo.domain.entity.Language;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Beka on 8/3/16.
 */
public class CloudStorage implements LanguagesStore {
    private final LanguagesService mService;
    private final LanguageDtoModelMapper mLanguageDtoModelMapper;

    @Inject
    public CloudStorage(LanguagesService service, LanguageDtoModelMapper languageDtoModelMapper) {
        mService = service;
        mLanguageDtoModelMapper = languageDtoModelMapper;
    }

    @Override
    public Observable<List<Language>> loadLanguageList() {
        return mService.getLanguages().map(mLanguageDtoModelMapper::map);
    }

    @Override
    public void saveLanguages(List<Language> languages) {
        throw new UnsupportedOperationException("You can not set data to cloud");
    }

    @Override
    public void clean() {
        throw new UnsupportedOperationException("You can not clear data from cloud");
    }
}
