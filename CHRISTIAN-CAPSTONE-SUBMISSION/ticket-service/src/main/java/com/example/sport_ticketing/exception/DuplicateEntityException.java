package com.example.sport_ticketing.exception;

public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException(String errorMessage) {
        super(errorMessage);
    }
}