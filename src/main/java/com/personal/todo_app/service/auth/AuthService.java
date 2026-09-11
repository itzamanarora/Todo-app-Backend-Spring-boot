package com.personal.todo_app.service.auth;

import com.personal.todo_app.dto.user.signin.SignInRequestDTO;
import com.personal.todo_app.dto.user.signin.SignInResponseDTO;
import com.personal.todo_app.dto.user.signup.SignUpRequestDTO;
import com.personal.todo_app.dto.user.signup.SignUpResponseDTO;

public interface AuthService {
    SignUpResponseDTO signUp(SignUpRequestDTO signUpRequestDTO);

    SignInResponseDTO signIn(SignInRequestDTO signInRequestDTO);
}
