package com.ses.playerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ExceptionResponse handleNoSuchElementException(
            NoSuchElementException exception, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setRequestedURI(request.getRequestURI());
        error.setErrorCode("Not found");

        return error;
    }
    @ExceptionHandler(DuplicateEntityException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ExceptionResponse handleDuplicateEntityException(
            DuplicateEntityException exception, final HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());
        error.setRequestedURI(request.getRequestURI());
        error.setErrorCode("Entity already exists");

        return error;
    }
}
