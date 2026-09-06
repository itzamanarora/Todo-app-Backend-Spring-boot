package com.personal.todo_app.controller.user;

import com.personal.todo_app.dto.user.UserResponseDTO;
import com.personal.todo_app.dto.user.signup.SignUpRequestDTO;
import com.personal.todo_app.dto.user.signup.SignUpResponseDTO;
import com.personal.todo_app.service.user.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDTO> signUp(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
        return ResponseEntity.status(
                        HttpStatus.CREATED)
                .body(userService.signUp(signUpRequestDTO));
    }
}
