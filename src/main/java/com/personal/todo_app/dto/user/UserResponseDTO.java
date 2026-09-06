package com.personal.todo_app.dto.user;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private UUID userId;
    private String username;
    private String email;
    private Instant createdAt;
    private Instant updatedAt;
}
