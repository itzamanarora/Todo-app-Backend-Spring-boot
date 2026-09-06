package com.personal.todo_app.mapper.user;

import com.personal.todo_app.dto.user.signup.SignUpRequestDTO;
import com.personal.todo_app.dto.user.signup.SignUpResponseDTO;
import com.personal.todo_app.models.user.User;

public class SignUpDTOMapper {

    public static User mapToUser(SignUpRequestDTO signUpRequestDTO) {
        return User.builder()
                .username(signUpRequestDTO.getUsername())
                .email(signUpRequestDTO.getEmail())
                .password(signUpRequestDTO.getPassword())
                .build();
    }

    public static SignUpResponseDTO mapToSignUpResponse(User user) {
        return SignUpResponseDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
