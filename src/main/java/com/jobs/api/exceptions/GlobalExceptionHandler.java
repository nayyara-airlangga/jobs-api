package com.jobs.api.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<Object> invalidBody(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(new ExceptionWrapper(errors));
    }

    @ExceptionHandler(value = {
            InternalAuthenticationServiceException.class,
            BadCredentialsException.class,
            IncorrectCredentialsException.class })
    public ResponseEntity<Object> incorrectCredentials(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(new ExceptionWrapper("Incorrect username or password"));
    }

    @ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class })
    public ResponseEntity<Object> methodNotSupported(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ExceptionWrapper("Method not allowed"));
    }

    @ExceptionHandler(value = { AccessDeniedException.class })
    public ResponseEntity<Object> accessDenied(AccessDeniedException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ExceptionWrapper("You are not allowed to access this resource"));
    }

    @ExceptionHandler(value = {
            HttpMessageNotReadableException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> badRequest(Exception e) {
        return ResponseEntity.badRequest().body(new ExceptionWrapper(e.getMessage()));
    }

    @ExceptionHandler(value = { InternalServerErrorException.class, Exception.class })
    public ResponseEntity<Object> handleServerExceptions(Exception e) {
        return ResponseEntity.internalServerError().body(new ExceptionWrapper(e.getMessage()));
    }
}
