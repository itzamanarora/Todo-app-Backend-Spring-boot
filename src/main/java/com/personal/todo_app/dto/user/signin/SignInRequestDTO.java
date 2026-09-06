package com.personal.todo_app.dto.user.signin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInRequestDTO {

    @NotBlank(message = "Email is required")
    @Size(max = 254, message = "Email must not be exceed 254 characters.")
    @Email(message = "Please provide a valid email.")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters.")
    private String password;
}
