package com.ashleyjoachim.triviaapp.model;


public class TriviaTokenRequest {
    private int response_code;
    private String response_message;
    private String token;

    public int getResponse_code() {
        return response_code;
    }

    public String getResponse_message() {
        return response_message;
    }

    public String getToken() {
        return token;
    }
}