package com.ashleyjoachim.triviaapp.network;

import com.ashleyjoachim.triviaapp.category.model.CategoryCountWrapper;
import com.ashleyjoachim.triviaapp.basemodel.TriviaTokenRequest;
import com.ashleyjoachim.triviaapp.basemodel.TriviaTokenReset;
import com.ashleyjoachim.triviaapp.basemodel.TriviaWrapperClass;

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

    @GET("api_count.php?")
    Single<CategoryCountWrapper> getQuestionCount(
            @Query("category") int category
    );

    @GET("api_token.php?")
    Single<TriviaTokenRequest> getTokenDiscover(
            @Query("command") String command
    );

    @GET("api_token.php?")
    Single<TriviaTokenReset> resetTokenDiscover(
            @Query("reset") String request,
            @Query("token") String token
    );
}
