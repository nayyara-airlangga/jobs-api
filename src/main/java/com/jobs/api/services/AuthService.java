package com.jobs.api.services;

import com.jobs.api.dtos.responses.LoginRes;

public interface AuthService {
    LoginRes login(String username, String password);
}
