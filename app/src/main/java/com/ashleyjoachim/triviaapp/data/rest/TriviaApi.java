package com.ashleyjoachim.triviaapp.data.rest;

import com.ashleyjoachim.triviaapp.data.model.TriviaWrapperClass;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TriviaApi {

    @GET("api_category.php?")
    Single<TriviaWrapperClass> getCategoryDiscover();

    @GET("api.php?")
    Single<TriviaWrapperClass> getTriviaDiscover(
            @Query("amount") int amount,
            @Query("category") int category,
            @Query("difficulty") String difficulty,
            @Query("token") String token
    );
}
