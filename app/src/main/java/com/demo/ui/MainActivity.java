package com.demo.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.demo.data.Injector;
import com.demo.data.api.oauth.OauthService;

import javax.inject.Inject;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.ObjectGraph;
import no.noname.baseapp.R;

public final class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.main_content) ViewGroup content;

    @BindColor(R.color.status_bar) int statusBarColor;

    @Inject ViewContainer viewContainer;

    private ObjectGraph activityGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();

        // Explicitly reference the application object since we don't want to match our own injector.
        ObjectGraph appGraph = Injector.obtain(getApplication());
        appGraph.inject(this);
        activityGraph = appGraph.plus(new MainActivityModule(this));

        ViewGroup container = viewContainer.forActivity(this);

        inflater.inflate(R.layout.main_activity, container);
        ButterKnife.bind(this, container);

        drawerLayout.setStatusBarBackgroundColor(statusBarColor);
    }

    @Override
    public Object getSystemService(@NonNull String name) {
        if (Injector.matchesService(name)) {
            return activityGraph;
        }
        return super.getSystemService(name);
    }

    @Override
    protected void onDestroy() {
        activityGraph = null;
        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Uri data = intent.getData();
        if (data == null) return;

        if ("u2020".equals(data.getScheme())) {
            Intent serviceIntent = new Intent(this, OauthService.class);
            serviceIntent.setData(data);
            startService(serviceIntent);
        }
    }
}
