package com.demo.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;

import com.demo.ui.rx_activity_result.RxActivityResult;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import no.noname.baseapp.R;


/**
 * Created by Beka on 8/12/16.
 */
public class SearchToolbar extends Toolbar {

    @BindView(R.id.search_view) AppCompatEditText mEditTextSearch;
    @BindView(R.id.search_clear) View mViewClearText;
    @BindView(R.id.voice_search) View mViewVoiceSearch;
    private TextWatcher mOnSearchTextChangeListener;


    public SearchToolbar(Context context) {
        super(context);
    }

    public SearchToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        setPopupTheme(android.support.v7.appcompat.R.style.ThemeOverlay_AppCompat_Light);
        setBackgroundResource(R.color.searchPrimary);
        View view = inflate(getContext(), R.layout.search_toolbar_container, this);
        ButterKnife.bind(this, view);

        mEditTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (mOnSearchTextChangeListener != null)mOnSearchTextChangeListener.beforeTextChanged(s, start, count, after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0) {
                    mViewVoiceSearch.setVisibility(View.GONE);
                    mViewClearText.setVisibility(View.VISIBLE);
                }else{
                    mViewVoiceSearch.setVisibility(View.VISIBLE);
                    mViewClearText.setVisibility(View.GONE);
                }
                if (mOnSearchTextChangeListener != null)mOnSearchTextChangeListener.onTextChanged(s, start, before, count);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mOnSearchTextChangeListener != null)mOnSearchTextChangeListener.afterTextChanged(s);
            }
        });
    }

    public void setOnSearchTextChangeListener(TextWatcher onSearchTextChangeListener) {
        mOnSearchTextChangeListener = onSearchTextChangeListener;
    }

    @OnClick(R.id.search_clear)
    public void clearSearchTerm(View v){
        mEditTextSearch.setText("");
        v.setVisibility(View.GONE);
        mViewVoiceSearch.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.voice_search)
    public void startVoiceSearch(View v){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getContext().getString(R.string.search_hint_say));

        RxActivityResult.on((Activity)getContext()).startIntent(intent)
                .subscribe(result -> {
                    Intent data = result.data();
                    int resultCode = result.resultCode();

                    if (resultCode == Activity.RESULT_OK) {
                        ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        if (matches.size() > 0) {
                            mEditTextSearch.setText(matches.get(0));
                        }
                    } else {

                    }
                });
    }
}
