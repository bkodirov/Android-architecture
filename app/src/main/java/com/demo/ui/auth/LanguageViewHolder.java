package com.demo.ui.auth;

import android.view.View;
import android.widget.TextView;

import com.demo.domain.entity.Language;
import com.demo.ui.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by Beka on 8/11/16.
 */
public class LanguageViewHolder extends BaseViewHolder{

@BindView(android.R.id.text1) TextView mTextView;
    public LanguageViewHolder(View itemView) {
        super(itemView);
    }
    public void bind(Language language){
        mTextView.setText(language.getName());
    }
}
