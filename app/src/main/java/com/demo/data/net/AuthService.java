package com.demo.data.net;

import com.demo.data.model.ResponseWrapper;
import com.demo.data.model.auth.CountriesData;
import com.demo.data.model.auth.SendSmsRequest;
import com.demo.data.model.auth.SmsSendResponseData;
import com.demo.data.model.auth.VerifyRequest;
import com.demo.domain.entity.User;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Beka on 8/8/16.
 */
public interface AuthService {

    @GET("/api/Account/countries")
    Observable<ResponseWrapper<CountriesData>> getCountries();

    @POST("/api/Account/RegisterBySMS")
    Observable<ResponseWrapper<SmsSendResponseData>> register(@Body SendSmsRequest sendSmsRequest);

    @POST ("/api/Account/VerifyBySms")
    Observable<ResponseWrapper<User>> verifyCode(@Body VerifyRequest verifyRequest);
}
