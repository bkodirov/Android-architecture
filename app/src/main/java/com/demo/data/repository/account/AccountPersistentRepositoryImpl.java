package com.demo.data.repository.account;


import com.demo.domain.entity.User;
import com.demo.domain.repository.AccountPersistentRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Beka on 8/6/16.
 */
public class AccountPersistentRepositoryImpl implements AccountPersistentRepository {

    @Inject
    public AccountPersistentRepositoryImpl() {
    }

    @Override
    public User readAccount() {
        return null;
    }

    @Override
    public void storeAccount(User user) {

    }

    @Override
    public void clean() {

    }

    @Override
    public Observable<User> signIn() {
        return null;
    }

    @Override
    public void logOut() {

    }
}
