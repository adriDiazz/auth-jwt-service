package com.authservice.authservice.controllers;

import com.authservice.authservice.commons.constants.ApiRoutes;
import com.authservice.authservice.commons.dto.LoginUserRequest;
import com.authservice.authservice.commons.dto.TokenResponse;
import com.authservice.authservice.commons.dto.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping( ApiRoutes.V1 + ApiRoutes.AUTH)
public interface AuthApi {
    @PostMapping("/register")
    ResponseEntity<TokenResponse> createUser(@RequestBody @Valid UserRequest userRequest);

    @PostMapping("/login")
    ResponseEntity<TokenResponse> loginUser(@RequestBody @Valid LoginUserRequest userRequest);
}
