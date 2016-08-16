package com.demo.ui.auth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.domain.entity.Language;
import com.demo.ui.misc.BindingAdapter;

import java.util.List;

/**
 * Created by Beka on 8/11/16.
 */
public class LanguagesAdapter extends BindingAdapter<Language, LanguageViewHolder> {
    private final List<Language> mLanguages;

    public LanguagesAdapter(Context context, List<Language> list) {
        super(context, list);
        mLanguages = list;
    }

    @Override
    public View newView(LayoutInflater inflater, int position, ViewGroup container) {
        return inflater.inflate(android.R.layout.simple_spinner_dropdown_item, container, false);
    }

    @Override
    public LanguageViewHolder buildViewHolder(View view) {
        return new LanguageViewHolder(view);
    }

    @Override
    public void bindView(Language item, int position, View view, LanguageViewHolder viewHolder) {
        viewHolder.bind(mLanguages.get(position));
    }

    @Override
    public View newDropDownView(LayoutInflater inflater, int position, ViewGroup container) {
        return inflater.inflate(android.R.layout.simple_spinner_dropdown_item, container, false);
    }

    @Override
    public void bindDropDownView(Language item, int position, View view, LanguageViewHolder viewHolder) {
        viewHolder.bind(mLanguages.get(position));
    }
}
