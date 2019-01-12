package com.ashleyjoachim.triviaapp.basemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TriviaTokenRequest {
    @Expose
    @SerializedName("response_code")
    private int responseCode;

    @Expose
    @SerializedName("response_message")
    private String responseMessage;

    @Expose
    @SerializedName("token")
    private String token;

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getToken() {
        return token;
    }
}
