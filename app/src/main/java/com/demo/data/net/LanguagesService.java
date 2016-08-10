package com.demo.data.net;


import com.demo.data.model.LanguageDto;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Beka on 8/3/16.
 */
public interface LanguagesService {
    @GET("/api/Data/GetLangs")
    Observable<List<LanguageDto>> getLanguages();
}
