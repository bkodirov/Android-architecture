package com.demo.ui.auth;

import com.demo.domain.entity.Language;

/**
 * Created by Beka on 8/15/16.
 */
public class Events {
    public static class  OpenSmsVerifier{

        private final String mPhoneNumber;
        private final Language mLanguage;

        public OpenSmsVerifier(String phoneNumber, Language language) {
            mPhoneNumber = phoneNumber;
            mLanguage = language;
        }

        public String getPhoneNumber() {
            return mPhoneNumber;
        }

        public Language getLanguage() {
            return mLanguage;
        }
    }
}
