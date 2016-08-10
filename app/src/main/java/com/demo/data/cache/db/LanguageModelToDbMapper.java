package com.demo.data.cache.db;


import com.demo.data.model.Mapper;
import com.demo.domain.entity.Language;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Beka on 8/4/16.
 */
public class LanguageModelToDbMapper implements Mapper< LanguageDbModel, Language> {
@Inject
    public LanguageModelToDbMapper() {
    }

    @Override
    public LanguageDbModel map(Language l) {
        return new LanguageDbModel(l.getName(), l.getCode(),l.getValue());
    }

    @Override
    public List<LanguageDbModel> map(List<Language> v) {
        List<LanguageDbModel> languageDbModelList=new ArrayList<>(v.size());
        for (Language l:v)languageDbModelList.add(map(l));
        return languageDbModelList;
    }
}
