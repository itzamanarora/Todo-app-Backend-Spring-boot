package com.personal.todo_app.dto.user.signup;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpResponseDTO {

    private UUID userId;
    private String username;
    private String email;
}
