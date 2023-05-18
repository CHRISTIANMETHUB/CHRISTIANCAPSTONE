package com.ses.playerservice.exception;

public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException(String errorMessage) {
        super(errorMessage);
    }
}