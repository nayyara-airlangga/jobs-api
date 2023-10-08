package com.jobs.api.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class JobDoesNotExistException extends RuntimeException {
    private String message = "Job does not exist";
}
