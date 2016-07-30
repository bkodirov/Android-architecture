package com.demo.data.api;


import com.demo.data.api.model.RepositoriesResponse;

import retrofit2.adapter.rxjava.Result;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface GithubService {
  @GET("search/repositories") //
  Observable<Result<RepositoriesResponse>> repositories( //
                                                         @Query("q") SearchQuery query, //
                                                         @Query("sort") Sort sort, //
                                                         @Query("order") Order order);
}
