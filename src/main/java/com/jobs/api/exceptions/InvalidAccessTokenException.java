package com.jobs.api.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class InvalidAccessTokenException extends RuntimeException {
    private String message = "Invalid access token";
}
