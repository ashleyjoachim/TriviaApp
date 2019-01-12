package com.ashleyjoachim.triviaapp.basemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TriviaTokenReset {
    @Expose
    @SerializedName("response_code")
    private int responseCode;

    @Expose
    @SerializedName("token")
    private String token;

    public int getResponseCode() {
        return responseCode;
    }

    public String getToken() {
        return token;
    }
}