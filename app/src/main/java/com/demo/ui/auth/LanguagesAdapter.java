package com.demo.ui.auth;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.domain.entity.Language;

import java.util.List;

/**
 * Created by Beka on 8/11/16.
 */
public class LanguagesAdapter extends RecyclerView.Adapter<LanguageViewHolder>{
    private final List<Language> mLanguages;

    public LanguagesAdapter(List<Language> languages) {
        mLanguages = languages;
    }

    @Override
    public LanguageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        return new LanguageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LanguageViewHolder holder, int position) {
        holder.bind(mLanguages.get(position));
    }

    @Override
    public int getItemCount() {
        return mLanguages.size();
    }
}
