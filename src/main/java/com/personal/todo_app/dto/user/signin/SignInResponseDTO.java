package com.personal.todo_app.dto.user.signin;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInResponseDTO {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private String role;
}
