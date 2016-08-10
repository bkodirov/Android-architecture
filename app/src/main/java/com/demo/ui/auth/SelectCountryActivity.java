package com.demo.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.demo.ui.BaseActivity;

import butterknife.BindView;
import no.noname.baseapp.R;

/**
 * Created by Beka on 8/10/16.
 */
public class SelectCountryActivity extends BaseActivity {
    @BindView(R.id.list) RecyclerView mRecyclerView;
    @BindView(R.id.toolbar) Toolbar mToolbar;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, SelectCountryActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomContentView(R.layout.activity_country);
    }
}
