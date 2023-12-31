package com.jobs.api.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class InternalServerErrorException extends RuntimeException {
    private String message = "Something went wrong";
}
