package com.demo.presenter.modules.splash;


import com.demo.domain.entity.Language;
import com.demo.domain.repository.LanguagesRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import timber.log.Timber;

/**
 * Created by Beka on 8/4/16.
 */
public class SplashPresenterImpl extends SplashPresenter {


    private final LanguagesRepository mLanguagesRepositoryImpl;

    @Inject
    public SplashPresenterImpl(LanguagesRepository languagesRepositoryImpl) {
        mLanguagesRepositoryImpl = languagesRepositoryImpl;
    }

    @Override
    public void synchPreloadData() {
        mLanguagesRepositoryImpl.getLanguages(false).subscribe(new Subscriber<List<Language>>() {
            @Override
            public void onCompleted() {
                Timber.d("onComplete");
                getView().loadDone();
            }

            @Override
            public void onError(Throwable e) {
                Timber.d("error %s", e);
            }

            @Override
            public void onNext(List<Language> languages) {
                Timber.d("onNext");
            }
        });
    }

    @Override
    public void onViewAttached() {

    }
}
