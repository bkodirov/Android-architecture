package com.demo.presenter.modules.auth;


import com.demo.domain.repository.CommonContentRepository;
import com.demo.domain.repository.LanguagesRepository;

import javax.inject.Inject;

/**
 * Created by Beka on 8/8/16.
 */
public class AuthPresenterImpl extends AuthPresenter {

    private final CommonContentRepository mCommonContentRepository;
    private final LanguagesRepository mLanguagesRepository;

    @Inject
    public AuthPresenterImpl(CommonContentRepository commonContentRepository, LanguagesRepository languagesRepository) {
        this.mCommonContentRepository = commonContentRepository;
        this.mLanguagesRepository = languagesRepository;
    }

    @Override
    public void fetchCountries() {
getView().showLoading();
        mCommonContentRepository.getCountries().subscribe(countries -> {

            getView().hideLoading();
        }, Throwable::printStackTrace, () -> getView().hideLoading());
    }

    @Override
    public void getSms() {
    }

    @Override
    public void selectCountry() {

    }

    @Override
    public void setCountry() {

    }

    @Override
    public void onViewAttached() {
        mLanguagesRepository.getLanguages(true).subscribe(languages -> getView().setLanguages(languages));
        fetchCountries();
    }
}
