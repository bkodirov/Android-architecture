package com.demo.data.cache.db;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

  public static final String NAME = "chatlas"; // we will add the .db extension

  public static final int VERSION = 1;
}