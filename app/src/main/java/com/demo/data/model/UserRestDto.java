package com.demo.data.model;


import static com.demo.util.Preconditions.checkNotNull;

public final class UserRestDto {
  public final String login;
  public final String avatar_url;

  public UserRestDto(String login, String avatar_url) {
    this.login = checkNotNull(login, "login == null");
    this.avatar_url = avatar_url;
  }

  @Override public String toString() {
    return "User{" +
        "login='" + login + '\'' +
        ", avatar_url='" + avatar_url + '\'' +
        '}';
  }
}
