package com.demo.ui.auth;

import android.view.View;
import android.widget.TextView;

import com.demo.DemoApp;
import com.demo.domain.entity.Country;
import com.demo.ui.base.BaseViewHolder;
import com.demo.ui.bus.RxBus;

import javax.inject.Inject;

import butterknife.BindView;
import no.noname.baseapp.R;

public class SimpleViewHolder extends BaseViewHolder {

    @BindView(R.id.textView) TextView mTextView;
    @Inject RxBus mRxBus;
    private Country mData;

    public SimpleViewHolder(View itemView) {
        super(itemView);
        DemoApp.obtain().inject(this);
        itemView.setOnClickListener(v -> mRxBus.post(mData));
    }

    public void bind(Country data) {
        mData = data;
        mTextView.setText(data.getName());
    }
}