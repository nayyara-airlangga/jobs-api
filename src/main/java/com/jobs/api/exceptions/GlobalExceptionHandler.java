package com.jobs.api.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { InternalServerErrorException.class, Exception.class })
    public ResponseEntity<Object> handleServerExceptions(Exception e) {
        return ResponseEntity.internalServerError().body(new ExceptionWrapper(e.getMessage()));
    }
}
