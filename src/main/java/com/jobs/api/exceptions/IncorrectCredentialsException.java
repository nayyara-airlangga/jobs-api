package com.jobs.api.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class IncorrectCredentialsException extends RuntimeException {
    private String message = "Incorrect username or password";
}
