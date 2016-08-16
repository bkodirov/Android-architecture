package com.demo.ui.auth;

import android.Manifest;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.demo.domain.entity.Country;
import com.demo.domain.entity.Language;
import com.demo.presenter.modules.auth.AuthPresenter;
import com.demo.presenter.modules.auth.AuthView;
import com.demo.ui.base.BaseFragment;
import com.demo.ui.bus.RxBus;
import com.demo.ui.rx_activity_result.Result;
import com.demo.ui.rx_activity_result.RxActivityResult;
import com.demo.ui.utils.UiUtils;
import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import no.noname.baseapp.R;
import rx.Observable;
import timber.log.Timber;

/**
 * Created by Beka on 8/8/16.
 */
public class AuthFragment extends BaseFragment implements AuthView {

    private Dialog mProgressDialog;
    private List<Language> mLanguages = new ArrayList<>();
    private LanguagesAdapter mLanguagesAdapter;

    @BindView(R.id.spinnerLanguage) Spinner mSpinnerLanguages;
    @BindView(R.id.textViewCountry) TextView mTextViewCountry;
    @BindView(R.id.phoneNumberEdit) TextInputEditText mTextInputEditTextPhoneNumber;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @Inject AuthPresenter mAuthPresenter;
    @Inject RxBus mRxBus;

    private List<Country> mCountryList;

    public static AuthFragment newInstance() {

        Bundle args = new Bundle();

        AuthFragment fragment = new AuthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        mProgressDialog = UiUtils.getProgressDialog(getContext());
        return inflater.inflate(R.layout.fragment_auth, null);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setSupportActionBar(mToolbar);
        mLanguagesAdapter = new LanguagesAdapter(getContext(), mLanguages);
        mSpinnerLanguages.setAdapter(mLanguagesAdapter);
        mAuthPresenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mAuthPresenter.detachView();
    }


    @Override
    public void showLoading() {
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        mProgressDialog.hide();
    }

    @Override
    public void showError(String error) {
        Snackbar.make(getView(), error, Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void setLanguages(List<Language> languages) {
        Timber.d("setLanguage");
        mLanguages.clear();
        mLanguages.addAll(languages);
        mLanguagesAdapter.notifyDataSetChanged();
    }

    @Override
    public void setCountry(Country country, List<Country> countryList) {
        mCountryList = countryList;
        mTextViewCountry.setText(country.getName());
        mTextInputEditTextPhoneNumber.setText("");
        mTextInputEditTextPhoneNumber.append(String.format("%s", country.getCode()));
    }

    @Override
    public void openSmsVerifier(String phoneNumber, Language language) {
        mRxBus.post(new Events.OpenSmsVerifier(phoneNumber, language));
    }


    @Override
    public Observable<Result<AuthFragment>> openCountrySelector() {
        if (mCountryList != null) {
            return RxActivityResult.on(this).startIntent(SelectCountryActivity.getIntent(getContext(), mCountryList));

        }
        return null;
    }

    @Override
    public Observable<Permission> checkNumberPermissions() {
        return RxPermissions.getInstance(getContext())
                .requestEach(Manifest.permission.READ_SMS,
                        Manifest.permission.READ_PHONE_STATE);
    }

    @OnClick(R.id.textViewCountry)
    public void onSelectCountryClick(View v) {
        openCountrySelector().subscribe(authFragmentResult -> {
            Country country = (Country) authFragmentResult.data().getSerializableExtra(SelectCountryActivity.EXTRA_CHOOSEN_COUNTRY);
            setCountry(country, mCountryList);
        });
    }

    @OnClick(R.id.auth)
    public void onAuthClick(View v) {
        String phoneNumber = mTextInputEditTextPhoneNumber.getText().toString();
        Language language = (Language) mSpinnerLanguages.getSelectedItem();
        mAuthPresenter.done(phoneNumber, language);
    }
}
