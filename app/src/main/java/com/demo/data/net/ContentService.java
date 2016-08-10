package com.demo.data.net;


import com.demo.domain.entity.Country;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Beka on 8/8/16.
 */
public interface ContentService {
    @GET("/api/Account/countries")
    Observable<List<Country>> getCountries();


}
