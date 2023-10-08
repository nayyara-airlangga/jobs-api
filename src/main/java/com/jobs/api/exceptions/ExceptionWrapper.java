package com.jobs.api.exceptions;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class ExceptionWrapper {
    public ExceptionWrapper(String message) {
        this.message = message;
    }

    public ExceptionWrapper(Map<String, String> messages) {
        this.messages = messages;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> messages;
}
