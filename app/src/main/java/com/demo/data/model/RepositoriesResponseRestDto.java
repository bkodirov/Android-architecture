package com.demo.data.model;

import java.util.List;

public final class RepositoriesResponseRestDto {
  public final List<RepositoryRestDto> items;

  public RepositoriesResponseRestDto(List<RepositoryRestDto> items) {
    this.items = items;
  }
}
