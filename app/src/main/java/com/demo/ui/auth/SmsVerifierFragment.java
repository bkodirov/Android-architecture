package com.demo.ui.auth;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsMessage;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.demo.domain.entity.Language;
import com.demo.presenter.modules.auth.SmsVerifierPresenter;
import com.demo.presenter.modules.auth.SmsVerifierView;
import com.demo.ui.base.BaseFragment;
import com.demo.ui.main.MainActivity;
import com.demo.ui.utils.UiUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import no.noname.baseapp.R;
import timber.log.Timber;

/**
 * Created by Beka on 8/15/16.
 */
public class SmsVerifierFragment extends BaseFragment implements SmsVerifierView {
    private static final String EXTRA_PHONE_NUMBER = "nuber";
    private static final String EXTRA_LANGUAGE = "language";

    @BindView(R.id.textViewYourNumber) TextView mTextViewYourNumber;
    @BindView(R.id.textViewTimer) TextView mTextViewTimer;
    @BindView(R.id.textViewResend) TextView mTextViewResend;
    @BindView(R.id.authCode) EditText mEditTextCode;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    private Dialog mProgressDialog;

    @Inject SmsVerifierPresenter mSmsVerifierPresenter;

    public static SmsVerifierFragment newInstance(String phoneNumber, Language language) {

        Bundle args = new Bundle();
        args.putString(EXTRA_PHONE_NUMBER, phoneNumber);
        args.putSerializable(EXTRA_LANGUAGE, language);
        SmsVerifierFragment fragment = new SmsVerifierFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        setSupportActionBar(mToolbar);
        mProgressDialog = UiUtils.getProgressDialog(getContext());
//        ActionBar bar = ((AppCompatActivity) getActivity()).getSupportActionBar();
//        bar.setHomeButtonEnabled(true);
        return inflater.inflate(R.layout.fragment_code_wait, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSmsVerifierPresenter.attachView(this);
        Language language = (Language) getArguments().getSerializable(EXTRA_LANGUAGE);
        String phoneNumber = getArguments().getString(EXTRA_PHONE_NUMBER);
        mSmsVerifierPresenter.getSms(phoneNumber, language);
        mTextViewYourNumber.setText(phoneNumber);

        mSmsVerifierPresenter.init(phoneNumber);
        mEditTextCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 4) {
                    mSmsVerifierPresenter.verifyCode(s.toString());
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mSmsVerifierPresenter.detachView();
    }

    @Override
    public void onResume() {
        super.onResume();
        getContext().registerReceiver(mReceiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
    }

    @Override
    public void onStop() {
        super.onStop();
        getContext().unregisterReceiver(mReceiver);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
            Timber.d("Bundle %s", bundle);
            SmsMessage[] msgs = null;
            String msg_from;
            if (bundle != null) {
                //---retrieve the SMS message received---
                try {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for (int i = 0; i < msgs.length; i++) {
                        msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        String msgBody = msgs[i].getMessageBody();
                    }
                } catch (Exception e) {
//                            Log.d("Exception caught",e.getMessage());
                }
            }
        }
    };

    @Override
    public void updateTimerView(String text) {
        if (mTextViewTimer != null) {
            mTextViewTimer.setText(text);
        }
    }

    @Override
    public void waitTimeIsUp() {
        if(mTextViewTimer !=null ) {
            mTextViewTimer.setText("00:00");
            mTextViewTimer.setVisibility(View.GONE);
            mTextViewResend.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void openMainWindow() {
        startActivity(MainActivity.getIntent(getContext()));
        getActivity().finish();
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

    @OnClick(R.id.textViewResend)
    public void onResendButtonClicked(View v) {
        mTextViewTimer.setVisibility(View.VISIBLE);
        v.setVisibility(View.GONE);

        Language language = (Language) getArguments().getSerializable(EXTRA_LANGUAGE);
        String string = getArguments().getString(EXTRA_PHONE_NUMBER);
        mSmsVerifierPresenter.getSms(string, language);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                getFragmentManager().popBackStack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
