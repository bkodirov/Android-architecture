package com.demo.domain.repository;

import com.demo.domain.entity.Language;

import java.util.List;

import rx.Observable;

/**
 * Created by Beka on 8/3/16.
 */
public interface LanguagesRepository {

    Observable<List<Language>> getLanguages(boolean onlyCached);

}
