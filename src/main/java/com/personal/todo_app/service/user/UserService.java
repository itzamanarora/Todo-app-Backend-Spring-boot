package com.personal.todo_app.service.user;

import com.personal.todo_app.dto.user.UserResponseDTO;
import com.personal.todo_app.dto.user.signup.SignUpRequestDTO;
import com.personal.todo_app.dto.user.signup.SignUpResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> getAllUsers();

    SignUpResponseDTO signUp(SignUpRequestDTO signUpRequestDTO);
}
