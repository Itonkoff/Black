package com.kofu.brighton.black.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiS {
    public static final String BASE_URL = "http://10.0.2.2:8000/";
    private static ApiS instance = null;
    private static ApiService service;
    private Retrofit retrofit;

    private ApiS(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);
    }

    public static ApiS getInstance() {
        if(instance == null){
            instance = new ApiS();
        }
        return instance;
    }

    public ApiService getService() {
        return service;
    }
}
