package com.demo.data.repository.languages;


import com.demo.domain.entity.Language;

import java.util.List;

import rx.Observable;

/**
 * Created by Beka on 8/3/16.
 */
public interface LanguagesStore {
    Observable<List<Language>> loadLanguageList();

    void saveLanguages(List<Language> languages);

    void clean();
}
