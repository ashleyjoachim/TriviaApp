package com.ashleyjoachim.triviaapp.network;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TriviaServiceGenerator {
    private static final String BASE_URL = "https://opentdb.com/";

    public static TriviaAPICall createService(){

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builderClient = new OkHttpClient.Builder();

        if(!builderClient.interceptors().contains(loggingInterceptor)){
            builderClient.addInterceptor(loggingInterceptor);
            builder.client(builderClient.build());
            retrofit = builder.build();
        }

        return retrofit.create(TriviaAPICall.class);
    }
}
