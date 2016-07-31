/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.domain.interactor;


import com.domain.entity.Order;
import com.domain.entity.SearchQuery;
import com.domain.entity.Sort;
import com.domain.executor.PostExecutionThread;
import com.domain.executor.ThreadExecutor;
import com.domain.repository.GitHubRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link User}.
 */
public class GetUserList extends UseCase {

    private final GitHubRepository mGitHubRepository;
    private SearchQuery mQuery;
    private Sort mSort;
    private Order mOrder;

    @Inject
    public GetUserList(GitHubRepository gitHubRepository,
                       ThreadExecutor threadExecutor,
                       PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.mGitHubRepository = gitHubRepository;
    }

    public GetUserList init(SearchQuery query, Sort sort, Order order){
        mQuery = query;
        mSort = sort;
        mOrder = order;
        return this;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return this.mGitHubRepository.getRepositories(mQuery, mSort, mOrder);
    }
}
