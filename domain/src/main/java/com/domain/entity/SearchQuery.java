package com.domain.entity;

import static com.domain.utils.Preconditions.checkNotNull;

public final class SearchQuery {
  private final long createdSince;

  private SearchQuery(Builder builder) {
    this.createdSince = checkNotNull(builder.createdSince, "createdSince == null");
  }

  @Override public String toString() {
    // Returning null here is not ideal, but it lets retrofit drop the query param altogether.
    return "created:>=" + createdSince;
  }

  public static final class Builder {
    private long createdSince;

    public Builder createdSince(long createdSince) {
      this.createdSince = createdSince;
      return this;
    }

    public SearchQuery build() {
      return new SearchQuery(this);
    }
  }
}
