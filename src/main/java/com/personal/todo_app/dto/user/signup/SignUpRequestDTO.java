package com.personal.todo_app.dto.user.signup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpRequestDTO {

    @NotBlank(message = "Username is required")
    @Size(max = 50, message = "Username must not be exceed 50 characters.")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email")
    @Size(max = 254, message = "Email must not be exceed 254 characters.")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(max = 255, message = "Password must not be exceed 255 characters.")
    private String password;
}
