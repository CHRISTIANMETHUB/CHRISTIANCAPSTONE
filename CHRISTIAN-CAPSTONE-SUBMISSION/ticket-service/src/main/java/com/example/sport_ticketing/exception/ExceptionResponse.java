package com.example.sport_ticketing.exception;

import lombok.Data;

@Data
public class ExceptionResponse {

    private String errorCode;
    private String errorMessage;
    private String requestedURI;
}