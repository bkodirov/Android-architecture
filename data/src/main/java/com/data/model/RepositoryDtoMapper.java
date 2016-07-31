/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.data.model;

import com.domain.entity.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link RepositoryRestDto} (in the data layer) to {@link Repository} in the
 * domain layer.
 */
@Singleton
public class RepositoryDtoMapper {

  @Inject
  public RepositoryDtoMapper() {}

  /**
   * Transform a {@link RepositoryRestDto} into an {@link Repository}.
   *
   * @param userEntity Object to be transformed.
   * @return {@link RepositoryRestDto} if valid {@link RepositoryRestDto} otherwise null.
   */
  public Repository transform(RepositoryRestDto userEntity) {
    Repository user = null;
    if (userEntity != null) {

    }
    return user;
  }

  /**
   * Transform a List of {@link RepositoryRestDto} into a Collection of {@link Repository}.
   *
   * @param userEntityCollection Object Collection to be transformed.
   * @return {@link Repository} if valid {@link RepositoryRestDto} otherwise null.
   */
  public List<Repository> transform(Collection<RepositoryRestDto> userEntityCollection) {
    List<Repository> userList = new ArrayList<>(20);
    Repository user;
    for (RepositoryRestDto userEntity : userEntityCollection) {
      user = transform(userEntity);
      if (user != null) {
        userList.add(user);
      }
    }

    return userList;
  }
}
