package com.data.model;

import static com.domain.utils.Preconditions.checkNotNull;

public final class RepositoryRestDto {
  public long id;
  public final String name;
  public final UserRestDto owner;
  public final String description;

  public final long watchers;
  public final long forks;

  public final String html_url;

  public final long updated_at;

  private RepositoryRestDto(Builder builder) {
    this.name = checkNotNull(builder.name, "name == null");
    this.owner = checkNotNull(builder.owner, "owner == null");
    this.description = builder.description;
    this.watchers = builder.stars;
    this.forks = builder.forks;
    this.html_url = checkNotNull(builder.htmlUrl, "html_url == null");
    this.updated_at = checkNotNull(builder.updatedAt, "updated_at == null");
    this.id = builder.id;
  }

  @Override public String toString() {
    return "Repository{" +
        "name='" + name + '\'' +
        ", owner=" + owner +
        ", description='" + description + '\'' +
        ", watchers=" + watchers +
        ", forks=" + forks +
        ", html_url='" + html_url + '\'' +
        ", updated_at=" + updated_at +
        '}';
  }

  public static final class Builder {
    private long id;
    private String name;
    private UserRestDto owner;
    private String description;
    private long stars;
    private long forks;
    private String htmlUrl;
    private long updatedAt;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder owner(UserRestDto owner) {
      this.owner = owner;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder stars(long stars) {
      this.stars = stars;
      return this;
    }

    public Builder forks(long forks) {
      this.forks = forks;
      return this;
    }

    public Builder htmlUrl(String htmlUrl) {
      this.htmlUrl = htmlUrl;
      return this;
    }

    public Builder id(long id){
      this.id = id;
      return this;
    }

    public Builder updatedAt(long updatedAt) {
      this.updatedAt = updatedAt;
      return this;
    }

    public RepositoryRestDto build() {
      return new RepositoryRestDto(this);
    }
  }
}
