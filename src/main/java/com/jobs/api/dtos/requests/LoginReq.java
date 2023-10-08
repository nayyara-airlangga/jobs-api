package com.jobs.api.dtos.requests;

import com.jobs.api.validators.Username;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginReq {
    @NotEmpty(message = "Username is required")
    @Username(message = "Invalid username")
    private String username;

    // Passwords must contain at least one lowercase char, one uppercase char, one
    // number, and one special symbol. They must have a length between 8-20
    // characters.
    @NotEmpty(message = "Password is required")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,20}$", message = "Invalid password")
    private String password;
}
