package com.demo.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import no.noname.baseapp.R;

/**
 * Created by Beka on 8/13/16.
 */
public class LceWidget extends FrameLayout {

    @BindView(R.id.contentGroup) ViewGroup contentGroup;
    @BindView(R.id.progressGroup) ViewGroup progressGroup;
    @BindView(R.id.emptyGroup) ViewGroup emptyGroup;
    @BindView(R.id.errorGroup) ViewGroup errorGroup;
//
    @BindView(R.id.list) RecyclerView mRecyclerView;
    @BindView(R.id.retryButton) Button mButtonRetry;


    {
        inflate(getContext(), R.layout.layout_list, this);
        ButterKnife.bind(this, this);
        initView();
    }

    public LceWidget(Context context) {
        super(context);
    }

    public LceWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LceWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LceWidget(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    public void showContent(RecyclerView.Adapter adapter) {
        contentGroup.setVisibility(View.VISIBLE);
        progressGroup.setVisibility(View.GONE);
        emptyGroup.setVisibility(View.GONE);
        errorGroup.setVisibility(View.GONE);

        mRecyclerView.setAdapter(adapter);
    }

    public void showErrorView(OnClickListener onClickListener) {
        contentGroup.setVisibility(View.GONE);
        progressGroup.setVisibility(View.GONE);
        emptyGroup.setVisibility(View.GONE);
        errorGroup.setVisibility(View.VISIBLE);
        mButtonRetry.setOnClickListener(onClickListener);
    }

    public void showEmptyView(){
        contentGroup.setVisibility(View.GONE);
        progressGroup.setVisibility(View.GONE);
        emptyGroup.setVisibility(View.VISIBLE);
        errorGroup.setVisibility(View.GONE);
    }

    public void showProgressView(){
        contentGroup.setVisibility(View.GONE);
        progressGroup.setVisibility(View.VISIBLE);
        emptyGroup.setVisibility(View.GONE);
        errorGroup.setVisibility(View.GONE);
    }
}
