package com.authservice.authservice.services.Impl;

import com.authservice.authservice.commons.dto.LoginUserRequest;
import com.authservice.authservice.commons.dto.TokenResponse;
import com.authservice.authservice.commons.dto.UserRequest;
import com.authservice.authservice.commons.entities.UserModel;
import com.authservice.authservice.repositories.UserRepository;
import com.authservice.authservice.services.AuthService;
import com.authservice.authservice.services.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository authRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository authRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.authRepository = authRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public TokenResponse createUser(UserRequest userRequest) {
        return Optional.of(userRequest)
                .map(this::mapToEntity)
                .map(authRepository::save)
                .map(userCreated -> jwtService.generateToken(userCreated.getId()))
                .orElseThrow(() -> new RuntimeException("Error creating user"));

    }

    @Override
    public TokenResponse loginUser(LoginUserRequest userRequest) {
        return authRepository.findByEmail(userRequest.getEmail())
                .filter(user -> passwordEncoder.matches(userRequest.getPassword(), user.getPassword()))
                .map(user -> jwtService.generateToken(user.getId()))
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }

    private UserModel mapToEntity(UserRequest userRequest) {
        return UserModel.builder()
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .email(userRequest.getEmail())
                .role("USER")
                .build();
    }
}
