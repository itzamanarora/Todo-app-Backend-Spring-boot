package com.personal.todo_app.service.auth.impl;

import com.personal.todo_app.models.user.User;
import com.personal.todo_app.service.auth.JwtService;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtServiceImpl implements JwtService {

    private final SecretKey secretKey;

    @Value(("${jwt.access-token-expiration}"))
    private long accessTokenExpiration;

    public JwtServiceImpl(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String generateAccessToken(User user) {
        Instant now = Instant.now();

        return Jwts.builder()
                .subject(user.getUserId().toString())
                .claim("email", user.getEmail())
                .claim("role", user.getRole().name())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(accessTokenExpiration)))
                .signWith(secretKey)
                .compact();
    }

    @Override
    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public UUID extractUserId(String token) {
        String userId = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        return UUID.fromString(userId);
    }
}
