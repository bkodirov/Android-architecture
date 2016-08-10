package com.demo.data.cache.db;


import com.demo.data.model.Mapper;
import com.demo.domain.entity.Language;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Beka on 8/3/16.
 */
public class LanguageDbModelMapper implements Mapper<Language, LanguageDbModel> {
    @Inject
    public LanguageDbModelMapper() {
    }

    @Override
    public Language map(LanguageDbModel l) {
        Language language = new Language(l.getName(), l.getCode(), l.getValue());
        return language;
    }

    @Override
    public List<Language> map(List<LanguageDbModel> v) {
        List<Language> languages = new ArrayList<>(v.size());
        for (LanguageDbModel l : v) languages.add(map(l));
        return languages;
    }
}
