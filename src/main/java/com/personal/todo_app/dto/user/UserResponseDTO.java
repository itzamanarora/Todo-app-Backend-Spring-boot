package com.personal.todo_app.dto.user;

import com.personal.todo_app.models.user.ROLES;
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
    private ROLES role;
    private Instant createdAt;
    private Instant updatedAt;
}
