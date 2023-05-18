package com.ses.gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(ApiGatewayException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ExceptionResponse handleDuplicateEntityException(
            ApiGatewayException exception) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setErrorCode("Entity already exists");

        return error;
    }
}
