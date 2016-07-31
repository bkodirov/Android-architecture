/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.data.repository.datasource;


import com.data.cache.UserCache;
import com.data.model.RepositoriesResponseRestDto;
import com.data.model.RepositoryRestDto;
import com.data.net.GithubService;
import com.domain.entity.Order;
import com.domain.entity.SearchQuery;
import com.domain.entity.Sort;

import java.util.List;

import retrofit2.adapter.rxjava.Result;
import rx.Observable;
import rx.functions.Action1;

/**
 * {@link UserDataStore} implementation based on connections to the api (Cloud).
 */
class CloudUserDataStore implements UserDataStore {

  private final GithubService mGithubService;
  private final UserCache userCache;

  private final Action1<RepositoriesResponseRestDto> saveToCacheAction = userEntity -> {
    if (userEntity != null) {
      CloudUserDataStore.this.userCache.put(userEntity.items);
    }
  };

  /**
   * Construct a {@link UserDataStore} based on connections to the api (Cloud).
   *
   * @param githubService The {@link GithubService} implementation to use.
   * @param userCache A {@link UserCache} to cache data retrieved from the api.
   */
  CloudUserDataStore(GithubService githubService, UserCache userCache) {
    mGithubService = githubService;
    this.userCache = userCache;
  }

  @Override
  public Observable<List<RepositoryRestDto>> repositoryList(SearchQuery query, Sort sort, Order order) {
    Observable<Result<RepositoriesResponseRestDto>> repositories = mGithubService.repositories(query, sort, order);
    return repositories.map(repositoriesResponseRestDtoResult -> repositoriesResponseRestDtoResult.response().body().items);
  }
}
