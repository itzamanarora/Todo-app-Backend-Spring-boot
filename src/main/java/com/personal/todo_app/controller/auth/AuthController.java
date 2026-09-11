package com.personal.todo_app.controller.auth;

import com.personal.todo_app.dto.user.signin.SignInRequestDTO;
import com.personal.todo_app.dto.user.signin.SignInResponseDTO;
import com.personal.todo_app.dto.user.signup.SignUpRequestDTO;
import com.personal.todo_app.dto.user.signup.SignUpResponseDTO;
import com.personal.todo_app.service.auth.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name="Auth Controller")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDTO> signUp(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
        return ResponseEntity.status(
                        HttpStatus.CREATED)
                .body(authService.signUp(signUpRequestDTO));
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDTO> signIn(@Valid @RequestBody SignInRequestDTO signInRequestDTO) {
        return ResponseEntity.ok(authService.signIn(signInRequestDTO));
    }
}
