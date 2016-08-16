package com.demo.ui.main;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.demo.domain.entity.User;
import com.demo.managers.AccountManager;
import com.demo.ui.base.BaseFragment;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import javax.inject.Inject;

import butterknife.BindView;
import no.noname.baseapp.R;

/**
 * Created by Behzodbek Qodirov on 09/17/15.
 */
public class SideMenuDrawerFragment extends BaseFragment {

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;

    @BindView(R.id.navigationView) NavigationView mNavigationView;
    @Inject AccountManager mAccountManager;
    @Inject Picasso mPicasso;



    public void setupDrawer(DrawerLayout upDrawer, Toolbar toolbar) {
        this.mDrawerLayout = upDrawer;
        mToolbar = toolbar;
        setUpNavigationDrawer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_side_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void initView() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.isCheckable())
                    menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                // TODO: 9/17/15 Custom event

                return true;
            }
        });

        NavigationDrawerHeaderView headerView = new NavigationDrawerHeaderView(getActivity());
        User user = mAccountManager.getAccount();

        String login = "";
        String firstName = user.getFirstName();
        String lastName = user.getLastName();

        if (!TextUtils.isEmpty(firstName)) {
            login = firstName;
        }
        if (!TextUtils.isEmpty(lastName)) {
            login += " " + lastName;
        }

        headerView.setUserLogin(login);

        mPicasso.load(user.getAvatar()).placeholder(R.drawable.profile_holder).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                headerView.setProfileImage(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                headerView.setProfileImage(errorDrawable);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                headerView.setProfileImage(placeHolderDrawable);
            }
        });

        mNavigationView.addHeaderView(headerView);
    }

    private void setUpNavigationDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, mToolbar, 0, 0);

        // Defer code dependent on restoration of previous instance state.
        mDrawerLayout.post(() -> mDrawerToggle.syncState());
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }
}
