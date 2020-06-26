package com.kofu.brighton.black.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    public static final String BASE_URL = "https://localhost:8000/";
    private static ApiService instance = null;
    private static IApi service;
    private Retrofit retrofit;

    private ApiService(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(IApi.class);
    }

    public static ApiService getInstance() {
        if(instance == null){
            instance = new ApiService();
        }
        return instance;
    }

    public static IApi getService() {
        return service;
    }
}
