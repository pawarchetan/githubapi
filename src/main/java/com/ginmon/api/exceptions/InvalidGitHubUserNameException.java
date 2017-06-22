package com.ginmon.api.exceptions;

public class InvalidGitHubUserNameException extends IllegalArgumentException {
    private int statusCode = 0;
    private String message = "";

    public InvalidGitHubUserNameException(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
