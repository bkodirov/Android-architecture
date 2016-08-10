package com.demo.data.net;

import com.demo.data.model.auth.SendSmsRequest;
import com.demo.data.model.auth.VerifyRequest;
import com.demo.data.model.response.Response;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Beka on 8/8/16.
 */
public interface AuthService {
    @POST("/api/Account/RegisterBySMS")
    Observable<Response> register(SendSmsRequest sendSmsRequest);
    @POST ("/api/Account/VerifyBySms")
    Observable<Response> verifyCode(VerifyRequest verifyRequest);
}
