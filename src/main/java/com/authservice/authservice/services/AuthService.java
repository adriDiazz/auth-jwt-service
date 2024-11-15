package com.authservice.authservice.services;

import com.authservice.authservice.commons.dto.LoginUserRequest;
import com.authservice.authservice.commons.dto.TokenResponse;
import com.authservice.authservice.commons.dto.UserRequest;

public interface AuthService {
    TokenResponse createUser(UserRequest userRequest);

    TokenResponse loginUser(LoginUserRequest userRequest);
}
