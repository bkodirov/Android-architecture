package com.demo.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;

import com.demo.domain.entity.Country;
import com.demo.ui.base.BaseActivity;
import com.demo.ui.bus.RxBus;
import com.demo.ui.widget.LceWidget;
import com.demo.ui.widget.SearchToolbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import no.noname.baseapp.R;
import timber.log.Timber;

/**
 * Created by Beka on 8/10/16.
 */
public class SelectCountryActivity extends BaseActivity {
    private static final String EXTRA_COUNTRIES = "countries";
    public static final String EXTRA_CHOOSEN_COUNTRY = "choosen country";
    @BindView(R.id.lceView) LceWidget mLceWidget;
    @BindView(R.id.toolbar) SearchToolbar mToolbar;
    @Inject RxBus mRxBus;

    public static Intent getIntent(Context context, List<Country> countries) {
        Intent intent = new Intent(context, SelectCountryActivity.class);
        intent.putExtra(EXTRA_COUNTRIES, (Serializable) countries);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomContentView(R.layout.activity_country);
        initView();
        initListeners();
    }

    private void initListeners() {
        mRxBus.register(Country.class, country -> {
            Intent i = getIntent();
            i.putExtra(EXTRA_CHOOSEN_COUNTRY, country);
            setResult(RESULT_OK, i);
            finish();
        });
    }

    private void initView() {
        setSupportActionBar(mToolbar);
        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        List<Country> countries = (List<Country>) getIntent().getSerializableExtra(EXTRA_COUNTRIES);
        Timber.d("Data %s", countries);
        List<Country> listAdapter = new ArrayList<>(countries);
        SimpleAdapter simpleAdapter = new SimpleAdapter(listAdapter);
        mLceWidget.showContent(simpleAdapter);

        mToolbar.setOnSearchTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                listAdapter.clear();
                listAdapter.addAll(countries);

                Iterator<Country> iterator = listAdapter.iterator();
                while (iterator.hasNext()) {
                    Country next = iterator.next();
                    if (!next.getName().contains(s.toString())) iterator.remove();
                }
                simpleAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(RESULT_CANCELED, getIntent());
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED, getIntent());
    }


}
