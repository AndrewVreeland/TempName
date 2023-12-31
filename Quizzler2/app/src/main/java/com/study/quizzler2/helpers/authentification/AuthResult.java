package com.study.quizzler2.helpers.authentification;

public class AuthResult {
    private boolean success;
    private String message;


    public AuthResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}