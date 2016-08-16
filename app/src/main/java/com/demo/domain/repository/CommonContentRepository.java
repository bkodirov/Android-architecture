package com.demo.domain.repository;

import rx.Observable;

/**
 * Created by Beka on 8/8/16.
 */
public interface CommonContentRepository {


    Observable<String> getMyCurrentCountry(String number);
}
