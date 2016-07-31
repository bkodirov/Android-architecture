package com.domain.entity;


import static com.domain.utils.Preconditions.checkNotNull;


public final class User {
  public final String login;
  public final String avatar_url;

  public User(String login,String avatar_url) {
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
