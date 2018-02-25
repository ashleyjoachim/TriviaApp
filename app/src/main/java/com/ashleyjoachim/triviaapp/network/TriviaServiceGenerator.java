package com.ashleyjoachim.triviaapp.network;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TriviaServiceGenerator {
    private static final String BASE_URL = "https://opentdb.com/";
    private static Retrofit.Builder builder =
            new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = builder.build();
    private static HttpLoggingInterceptor loggingInterceptor =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private static OkHttpClient.Builder builderClient =
            new OkHttpClient.Builder();

    public static TriviaAPICall createService(){

        if(!builderClient.interceptors().contains(loggingInterceptor)){
            builderClient.addInterceptor(loggingInterceptor);
            builder.client(builderClient.build());
            retrofit = builder.build();
        }
        return retrofit.create(TriviaAPICall.class);
    }
}
