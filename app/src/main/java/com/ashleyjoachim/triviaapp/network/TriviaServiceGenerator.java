package com.ashleyjoachim.triviaapp.network;


import com.ashleyjoachim.triviaapp.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TriviaServiceGenerator {

    private static final String BASE_URL = "https://opentdb.com/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {

        if (retrofit == null) {

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            OkHttpClient okHttpClient = builder.build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        return retrofit;
    }

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
