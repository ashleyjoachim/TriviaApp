package com.ashleyjoachim.triviaapp.network;


import com.ashleyjoachim.triviaapp.category.model.CategoryCountWrapper;
import com.ashleyjoachim.triviaapp.basemodel.TriviaTokenRequest;
import com.ashleyjoachim.triviaapp.basemodel.TriviaTokenReset;
import com.ashleyjoachim.triviaapp.basemodel.TriviaWrapperClass;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TriviaAPICall {

    @GET("api_category.php?")
    Observable<TriviaWrapperClass> getCategoryDiscover();

    @GET("api_count.php?")
    Observable<CategoryCountWrapper> getQuestionCount(
            @Query("category") int category
    );

    @GET("api_token.php?")
    Observable<TriviaTokenRequest> getTokenDiscover(
            @Query("command") String command
    );

    @GET("api_token.php?")
    Observable<TriviaTokenReset> resetTokenDiscover(
            @Query("reset") String request,
            @Query("token") String token
    );

    @GET("api.php?")
    Observable<TriviaWrapperClass> getTriviaDiscover(
            @Query("amount") int amount,
            @Query("category") int category,
            @Query("difficulty") String difficulty,
            @Query("token") String token
    );

}
