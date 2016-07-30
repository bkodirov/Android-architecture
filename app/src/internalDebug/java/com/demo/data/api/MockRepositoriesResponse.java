package com.demo.data.api;

import com.demo.data.api.model.RepositoriesResponse;

import java.util.Arrays;
import java.util.Collections;

import static com.demo.data.api.MockRepositories.BUTTERKNIFE;
import static com.demo.data.api.MockRepositories.DAGGER;
import static com.demo.data.api.MockRepositories.JAVAPOET;
import static com.demo.data.api.MockRepositories.MOSHI;
import static com.demo.data.api.MockRepositories.OKHTTP;
import static com.demo.data.api.MockRepositories.OKIO;
import static com.demo.data.api.MockRepositories.PICASSO;
import static com.demo.data.api.MockRepositories.RETROFIT;
import static com.demo.data.api.MockRepositories.SQLBRITE;
import static com.demo.data.api.MockRepositories.TELESCOPE;
import static com.demo.data.api.MockRepositories.U2020;
import static com.demo.data.api.MockRepositories.WIRE;

public enum MockRepositoriesResponse {
  SUCCESS("Success", new RepositoriesResponse(Arrays.asList( //
      BUTTERKNIFE, //
      DAGGER, //
      JAVAPOET, //
      OKHTTP, //
      OKIO, //
      PICASSO, //
      RETROFIT, //
      SQLBRITE, //
      TELESCOPE, //
      U2020, //
      WIRE, //
      MOSHI))),
  ONE("One", new RepositoriesResponse(Collections.singletonList(DAGGER))),
  EMPTY("Empty", new RepositoriesResponse(null));

  public final String name;
  public final RepositoriesResponse response;

  MockRepositoriesResponse(String name, RepositoriesResponse response) {
    this.name = name;
    this.response = response;
  }

  @Override public String toString() {
    return name;
  }
}
