package com.ginmon.api.controllers;

import com.ginmon.api.exceptions.GitHubServiceError;
import com.ginmon.api.exceptions.InvalidGitHubRepositoryException;
import com.ginmon.api.exceptions.InvalidGitHubUserNameException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@ControllerAdvice
public class DefaultControllerAdvice {
    @ResponseStatus(NO_CONTENT)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleMethodArgumentNotValidException() {
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(InvalidGitHubUserNameException.class)
    public ResponseEntity<GitHubServiceError> handleInvalidUserNameException(InvalidGitHubUserNameException invalidGitHubUserNameException){
        GitHubServiceError error = new GitHubServiceError();
        error.setStatusCode(invalidGitHubUserNameException.getStatusCode());
        error.setMessage("GitHub user not found");
        return new ResponseEntity<>(error, NOT_FOUND);
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(InvalidGitHubRepositoryException.class)
    public ResponseEntity<GitHubServiceError> handleServiceException(
            InvalidGitHubRepositoryException invalidGitHubRepositoryException) {
        GitHubServiceError error = new GitHubServiceError();
        error.setStatusCode(invalidGitHubRepositoryException.getStatusCode());
        error.setMessage("GitHub repository or user not found");
        return new ResponseEntity<>(error, NOT_FOUND);
    }
}
