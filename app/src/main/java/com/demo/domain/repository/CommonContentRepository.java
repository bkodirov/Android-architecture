package com.demo.domain.repository;

import com.demo.domain.entity.Country;

import java.util.List;

import rx.Observable;

/**
 * Created by Beka on 8/8/16.
 */
public interface CommonContentRepository {
    Observable<List<Country>> getCountries();
}
