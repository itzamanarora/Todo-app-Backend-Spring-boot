package com.personal.todo_app.service.auth;

import com.personal.todo_app.models.user.User;

import java.util.UUID;

public interface JwtService {
    String generateAccessToken(User user);

    boolean validateAccessToken(String token);

    UUID extractUserId(String token);
}
