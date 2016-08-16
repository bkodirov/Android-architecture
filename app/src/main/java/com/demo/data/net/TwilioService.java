package com.demo.data.net;

import com.demo.data.model.auth.TwilioResponse;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Beka on 8/11/16.
 */
public interface TwilioService {

    @GET("/PhoneNumbers/{number}?Type=carrier&Type=caller-name")
    @Headers({"AuthToken: c94b69e3b209a8bae493617d76528ab3", "AccountSid: ACe9e1edd0d8f2a77e81cc85613101e26d"})
    Observable<TwilioResponse> getInfo(@Path("number") String number);
}
