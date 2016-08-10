package com.demo.data.repository.languages;

import android.content.res.Resources;

import com.demo.domain.entity.Language;
import com.demo.domain.repository.LanguagesRepository;
import com.fernandocejas.frodo.annotation.RxLogObservable;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Beka on 8/3/16.
 */
public class LanguagesRepositoryImpl implements LanguagesRepository {
    private final LocalStorage mLocalStorage;
    private final CloudStorage mCloudStorage;

    @Inject
    public LanguagesRepositoryImpl(LocalStorage localStorage, CloudStorage cloudStorage) {
        this.mLocalStorage = localStorage;
        this.mCloudStorage = cloudStorage;
    }

    @Override
    @RxLogObservable
    public Observable<List<Language>> getLanguages(boolean onlyCached) {
        Observable<List<Language>> listObservable;
        if (onlyCached) {
            listObservable = mLocalStorage.loadLanguageList();
        } else {
            listObservable = Observable.create(new Observable.OnSubscribe<List<Language>>() {
                @Override
                public void call(Subscriber<? super List<Language>> subscriber) {
                    mCloudStorage.loadLanguageList().subscribe(new Subscriber<List<Language>>() {
                        @Override
                        public void onCompleted() {
                            Timber.d("from cloud complete");
                            subscriber.onCompleted();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Timber.e("from cloud error, %s", e);
                            mLocalStorage.loadLanguageList().subscribe(new Subscriber<List<Language>>() {
                                @Override
                                public void onCompleted() {
                                    Timber.d("from db complete");
                                    subscriber.onCompleted();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Timber.e("from bd error");
                                    subscriber.onError(e);
                                }

                                @Override
                                public void onNext(List<Language> languages) {
                                    Timber.d("onNext db %s",languages);
                                    if (languages == null || languages.isEmpty()) {
                                        subscriber.onError(new Resources.NotFoundException("Data not found"));
                                    } else {
                                        subscriber.onNext(languages);
                                    }
                                }
                            });
                        }

                        @Override
                        public void onNext(List<Language> languages) {
                            Timber.d("onNext cloud %s",languages);
                            mLocalStorage.clean();
                            mLocalStorage.saveLanguages(languages);
                            subscriber.onNext(languages);
                        }
                    });
                }
            });
        }


        return listObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
