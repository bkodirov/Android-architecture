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
package com.data.repository;


import com.data.model.RepositoryDtoMapper;
import com.data.repository.datasource.UserDataStore;
import com.data.repository.datasource.UserDataStoreFactory;
import com.domain.entity.Order;
import com.domain.entity.Repository;
import com.domain.entity.SearchQuery;
import com.domain.entity.Sort;
import com.domain.repository.GitHubRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * {@link GitHubRepository} for retrieving user data.
 */
@Singleton
public class GitHubDataRepository implements GitHubRepository {

  private final UserDataStoreFactory userDataStoreFactory;
  private final RepositoryDtoMapper mRepositoryDtoMapper;

  /**
   * Constructs a {@link GitHubRepository}.
   *
   * @param dataStoreFactory A factory to construct different data source implementations.
   */
  @Inject
  public GitHubDataRepository(UserDataStoreFactory dataStoreFactory,
                              RepositoryDtoMapper repositoryDtoMapper) {
    this.userDataStoreFactory = dataStoreFactory;
    mRepositoryDtoMapper = repositoryDtoMapper;
  }


  @Override
  public Observable<List<Repository>> getRepositories(SearchQuery query, Sort sort, Order order) {
    //we always get all getRepositories from the cloud
    final UserDataStore userDataStore = this.userDataStoreFactory.createCloudDataStore();
    return userDataStore.repositoryList(query, sort, order).map(mRepositoryDtoMapper::transform);
  }
}
