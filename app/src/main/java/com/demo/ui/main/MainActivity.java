package com.demo.ui.main;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.ViewGroup;

import com.demo.ui.BaseActivity;

import butterknife.BindColor;
import butterknife.BindView;
import no.noname.baseapp.R;

public final class MainActivity extends BaseActivity {
    @BindView(R.id.main_drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.main_content) ViewGroup content;

    @BindColor(R.color.status_bar) int statusBarColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomContentView(R.layout.main_activity);
        drawerLayout.setStatusBarBackgroundColor(statusBarColor);
    }


}
