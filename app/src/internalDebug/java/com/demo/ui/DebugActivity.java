package com.demo.ui;

import android.app.Activity;
import android.os.Bundle;

import com.demo.DemoApp;

import dagger.ObjectGraph;
import no.noname.baseapp.R;

public final class DebugActivity extends Activity {
  private ObjectGraph appGraph;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    appGraph = DemoApp.obtain();
    setContentView(R.layout.debug_activity);
  }
}
