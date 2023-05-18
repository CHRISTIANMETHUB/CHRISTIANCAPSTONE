package com.ses.gateway.exception;

public class ApiGatewayException extends RuntimeException {
    public ApiGatewayException(String errorMessage) {
        super(errorMessage);
    }
}