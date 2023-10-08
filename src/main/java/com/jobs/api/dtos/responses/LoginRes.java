package com.jobs.api.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LoginRes {
    @JsonProperty("access_token")
    private String accessToken;
}
