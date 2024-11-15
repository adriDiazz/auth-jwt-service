package com.authservice.authservice.services;

import com.authservice.authservice.commons.dto.TokenResponse;
import io.jsonwebtoken.Claims;

public interface JwtService {

    TokenResponse generateToken(Long userId);
    Claims getClaimsFromToken(String token);
    boolean isExpired(String token);
    Integer extractUserId(String token);


}
