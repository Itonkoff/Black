package com.kofu.brighton.black.services;

import com.kofu.brighton.black.dtos.AuthenticationDto;
import com.kofu.brighton.black.dtos.HistoryDto;
import com.kofu.brighton.black.dtos.HistoryForPredictionDto;
import com.kofu.brighton.black.dtos.UserLoginDto;
import com.kofu.brighton.black.market.History;
import com.kofu.brighton.black.user.Credentials;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("/accounts/token/login")
    Call<AuthenticationDto> login(@Body UserLoginDto credentials);



    @POST("/accounts/token/logout")
    Call<Credentials> logOut(@Header("Authorization") String token);



    @Headers({
            "Accept: application/json"
    })
    @GET("/market/history/")
    Call<List<HistoryDto>> getHistory(@Header("Authorization") String token);



    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
    })
    @POST("/market/history/")
    Call<HistoryDto> placePrediction(@Header("Authorization") String token, @Body HistoryForPredictionDto predictionValues);

}
