package com.demo.data.repository.languages;

import com.demo.data.model.LanguageDto;
import com.demo.data.model.Mapper;
import com.demo.domain.entity.Language;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Beka on 8/3/16.
 */
public class LanguageDtoModelMapper implements Mapper<Language, LanguageDto> {
    @Inject
    public LanguageDtoModelMapper() {
    }

    @Override
    public Language map(LanguageDto l) {
        Language language = new Language(l.getName(), l.getCode(), l.getValue());
        return language;
    }

    @Override
    public List<Language> map(List<LanguageDto> v) {
        List<Language> languages = new ArrayList<>(v.size());
        for (LanguageDto l : v) languages.add(map(l));
        return languages;
    }
}
