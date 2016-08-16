package com.demo.managers;

import com.demo.data.cache.SerializerHelper;
import com.demo.domain.entity.User;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Manager class which deals with User Account. All User account related business logic shuold be here.
 */
public final class AccountManager {
    private static final String ACCOUNT_FILE_NAME = "data.dat";
    private User mAccount;

    @Inject
    public AccountManager() {
        this.mAccount = readStoredAccount();
        //Check if user already exists and it is valid user set token to all NetWork requests
    }


    /**
     * Log out from app. In order to proper log out must be removed token from network requests.
     * Clear Helpshift avvount. Stop Playback proccess, send information about it to Analytics trackers.
     */
    public synchronized void logOut() {
        mAccount = null;
        storeAccount(mAccount);
    }

    /**
     * Checks, is any valid stored account here.
     *
     * @return
     */
    public boolean isAuthorized() {
        return mAccount != null;
    }


    /**
     * Read Serialized Account object from the Disk.
     *
     * @return
     */
    private User readStoredAccount() {
        User account = SerializerHelper.readObject(ACCOUNT_FILE_NAME);
        return account;
    }

    /**
     * Save serialized account in the Disk
     *
     * @param account
     */
    public void storeAccount(User account) {
        Timber.d("Storing account %s", account);
        SerializerHelper.storeObject(account, ACCOUNT_FILE_NAME);

    }

    public User getAccount() {
        return mAccount;
    }
}
