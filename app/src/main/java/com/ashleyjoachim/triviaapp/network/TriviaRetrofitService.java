package com.ashleyjoachim.triviaapp.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ashleyjoachim.triviaapp.Constants.BASE_URL;

public class TriviaRetrofitService {

    public static TriviaApi getTriviaApi() {
        return getRetrofit().create(TriviaApi.class);
    }

    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
