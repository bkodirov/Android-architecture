package com.demo.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.demo.ui.BaseActivity;

import no.noname.baseapp.R;

/**
 * Created by Beka on 8/4/16.
 */
public class AuthActivity extends BaseActivity {

    public static Intent getIntent(Context context) {
        Intent i = new Intent(context, AuthActivity.class);
        return i;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomContentView(R.layout.activity_auth);
        changeFragment(AuthFragment.newInstance(), R.id.container);
    }
}
