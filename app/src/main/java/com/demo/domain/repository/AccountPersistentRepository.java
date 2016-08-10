package com.demo.domain.repository;

import com.demo.domain.entity.User;

import rx.Observable;

/**
 * Created by Beka on 8/5/16.
 */
public interface AccountPersistentRepository {

    User readAccount();

    void storeAccount(User user);

    void clean();

    Observable<User> signIn();

    void logOut();
}
