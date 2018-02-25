package com.ashleyjoachim.triviaapp.network;


import com.ashleyjoachim.triviaapp.model.TriviaWrapperClass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TriviaAPICall {
    @GET("api.php?")
    Call<TriviaWrapperClass> getTriviaDiscover(
            @Query("category") int category,
            @Query("difficulty") String difficulty
    );

    @GET("api_category.php")
    Call<TriviaWrapperClass> getCategoryDiscover();

}
