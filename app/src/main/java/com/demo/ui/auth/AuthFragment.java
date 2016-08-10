package com.demo.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.demo.domain.entity.Country;
import com.demo.domain.entity.Language;
import com.demo.presenter.modules.auth.AuthPresenter;
import com.demo.presenter.modules.auth.AuthView;
import com.demo.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import no.noname.baseapp.R;

/**
 * Created by Beka on 8/8/16.
 */
public class AuthFragment extends BaseFragment implements AuthView {

    private static final int REQUEST_SELECT_COUNTRY = 901234;
    private List<Language> mLanguages=new ArrayList<>();
    private LanguagesAdapter mLanguagesAdapter=new LanguagesAdapter(mLanguages);

    @BindView(R.id.spinnerLanguage)Spinner mSpinnerLanguages;
    @Inject AuthPresenter mAuthPresenter;

    public static AuthFragment newInstance() {

        Bundle args = new Bundle();

        AuthFragment fragment = new AuthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_auth, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuthPresenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mAuthPresenter.detachView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setLanguages(List<Language> languages) {
        mLanguages.clear();
        mLanguages.addAll(languages);
        mLanguagesAdapter.notifyDataSetChanged();
    }

    @Override
    public void setCountries(Country countrie) {

    }

    @Override
    public void openSmsVerifier() {

    }

    @Override
    public void openCountrySelector() {
        startActivityForResult(SelectCountryActivity.getIntent(getContext()), REQUEST_SELECT_COUNTRY);
    }

    @OnClick(R.id.textViewCountry)
    public void onSeclectCountryClick(View v){
        mAuthPresenter.selectCountry();
    }
}
