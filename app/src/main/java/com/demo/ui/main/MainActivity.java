package com.demo.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.demo.ui.base.BaseActivity;

import butterknife.BindView;
import no.noname.baseapp.R;

public final class MainActivity extends BaseActivity {
    @BindView(R.id.main_content) ViewGroup content;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.main_drawer_layout) DrawerLayout mDrawerLayout;

    public static Intent getIntent(Context context){
        Intent i=new Intent(context, MainActivity.class);
        return i;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomContentView(R.layout.main_activity);
        setupToolbar();
        setupSideMenu();

    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
    }

    private void setupSideMenu() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.content_menu_fragment);
        if (f instanceof SideMenuDrawerFragment) {
            ((SideMenuDrawerFragment) f).setupDrawer(mDrawerLayout, mToolbar);
        }
    }


}
