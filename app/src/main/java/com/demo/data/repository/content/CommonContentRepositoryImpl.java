package com.demo.data.repository.content;

import com.demo.data.net.ContentService;
import com.demo.domain.entity.Country;
import com.demo.domain.repository.CommonContentRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Beka on 8/8/16.
 */
public class CommonContentRepositoryImpl implements CommonContentRepository {
    private final ContentService mContentService;

    @Inject
    public CommonContentRepositoryImpl(ContentService contentService) {
        mContentService = contentService;
    }

    @Override
    public Observable<List<Country>> getCountries() {
        return mContentService.getCountries();
    }
}
