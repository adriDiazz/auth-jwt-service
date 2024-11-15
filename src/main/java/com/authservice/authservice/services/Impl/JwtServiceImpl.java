package com.authservice.authservice.services.Impl;

import com.authservice.authservice.commons.dto.TokenResponse;
import com.authservice.authservice.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService  {

    private final String secret;

    public JwtServiceImpl(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }


    @Override
    public TokenResponse generateToken(Long userId) {
        Date expirationDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24);

        String token = Jwts.builder()
                .setSubject(userId.toString())
                .setExpiration(expirationDate)
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secret)
                .compact();


        return TokenResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public boolean isExpired(String token) {
        try {
            return getClaimsFromToken(token).getExpiration().before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public Integer extractUserId(String token) {
        try {
            return Integer.parseInt(getClaimsFromToken(token).getSubject());
        } catch (Exception e) {
            return null;
        }
    }
}
