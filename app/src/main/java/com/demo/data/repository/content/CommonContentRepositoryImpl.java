package com.demo.data.repository.content;

import com.demo.data.model.auth.TwilioResponse;
import com.demo.data.net.ContentService;
import com.demo.data.net.TwilioService;
import com.demo.domain.repository.CommonContentRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Beka on 8/8/16.
 */
public class CommonContentRepositoryImpl implements CommonContentRepository {
    private final ContentService mContentService;
    private final TwilioService mTwilioService;

    @Inject
    public CommonContentRepositoryImpl(ContentService contentService, TwilioService twilioService) {
        mContentService = contentService;
        mTwilioService = twilioService;
    }

    @Override
    public Observable<String> getMyCurrentCountry(String number) {
        return mTwilioService.getInfo(number).map(TwilioResponse::getCountryCode).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());}
}
