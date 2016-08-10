package com.demo.domain.entity;


import static com.demo.util.Preconditions.checkNotNull;

public final class User {
    private final String login;
    private String token;
    private final String avatar_url;

    public User(String login, String avatar_url) {
        this.login = checkNotNull(login, "login == null");
        this.avatar_url = avatar_url;
    }

    public String getLogin() {
        return login;
    }

    public String getToken() {
        return token;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public boolean isAuthorized() {
        return login != null && login.trim().length() > 0
                && token != null && token.trim().length() > 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                '}';
    }
}
