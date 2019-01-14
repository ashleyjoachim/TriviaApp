package com.ashleyjoachim.triviaapp.data.rest;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ashleyjoachim.triviaapp.util.Constants.BASE_URL;

public class TriviaServiceProvider {

    public static TriviaApi provideTriviaApi() {
        return retrofit().create(TriviaApi.class);
    }

    private static Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
