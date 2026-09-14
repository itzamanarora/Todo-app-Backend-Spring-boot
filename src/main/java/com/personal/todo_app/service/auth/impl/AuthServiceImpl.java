package com.personal.todo_app.service.auth.impl;

import com.personal.todo_app.dto.user.signin.SignInRequestDTO;
import com.personal.todo_app.dto.user.signin.SignInResponseDTO;
import com.personal.todo_app.dto.user.signup.SignUpRequestDTO;
import com.personal.todo_app.dto.user.signup.SignUpResponseDTO;
import com.personal.todo_app.exception.auth.InvalidCredentialsException;
import com.personal.todo_app.mapper.user.SignUpDTOMapper;
import com.personal.todo_app.models.user.User;
import com.personal.todo_app.repository.user.UserRepository;
import com.personal.todo_app.service.auth.AuthService;
import com.personal.todo_app.service.auth.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.module.InvalidModuleDescriptorException;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public SignUpResponseDTO signUp(SignUpRequestDTO signUpRequestDTO) {
        String passwordHash = passwordEncoder.encode(
                signUpRequestDTO.getPassword()
        );

        User user = User.builder()
                .username(signUpRequestDTO.getUsername())
                .email(signUpRequestDTO.getEmail())
                .password(passwordHash)
                .build();

        User savedUser = userRepository.save(user);
        log.info("New user created successfully.");
        return SignUpDTOMapper.mapToSignUpResponse(savedUser);
    }

    @Override
    public SignInResponseDTO signIn(SignInRequestDTO signInRequestDTO) {
        User user = userRepository.findByEmail(signInRequestDTO.getEmail())
                .orElseThrow(InvalidCredentialsException::new);

        boolean passwordHash = passwordEncoder.matches(signInRequestDTO.getPassword(), user.getPassword());

        if (!passwordHash) throw new InvalidCredentialsException();

        String accessToken = jwtService.generateAccessToken(user);

        log.info("User Logged in: {}", user.getEmail());

        return SignInResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken("hello")
                .tokenType("Bearer")
                .role(String.valueOf(user.getRole()))
                .build();
    }
}
