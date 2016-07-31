package com.data.net;


import com.data.model.RepositoriesResponseRestDto;
import com.domain.entity.Order;
import com.domain.entity.SearchQuery;
import com.domain.entity.Sort;

import retrofit2.adapter.rxjava.Result;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface GithubService {
    @GET("search/repositories") //
    Observable<Result<RepositoriesResponseRestDto>> repositories(@Query("q") SearchQuery query, @Query("sort") Sort sort, @Query("order") Order order);
}
