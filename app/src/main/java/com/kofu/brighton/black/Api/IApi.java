package com.kofu.brighton.black.Api;

import com.kofu.brighton.black.Market.History;
import com.kofu.brighton.black.User.Authentication;
import com.kofu.brighton.black.User.Credentials;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IApi {
    @POST("/accounts/token/login")
    Call login(@Body Credentials credentials);

    @POST("/accounts/token/logout")
    Call logOut(@Body Credentials credentials);

    @GET("market/history")
    Call<List<History>> getAllHistory();

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    @POST("market/history")
    Call history(@Header("Authorization") String token, @Body History history);// TODO: 6/26/2020 Concatenate "Token " with token

}
