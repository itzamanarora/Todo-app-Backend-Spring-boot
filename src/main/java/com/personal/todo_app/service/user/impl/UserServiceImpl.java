package com.personal.todo_app.service.user.impl;

import com.personal.todo_app.dto.user.UserResponseDTO;
import com.personal.todo_app.dto.user.signup.SignUpRequestDTO;
import com.personal.todo_app.dto.user.signup.SignUpResponseDTO;
import com.personal.todo_app.mapper.user.SignUpDTOMapper;
import com.personal.todo_app.mapper.user.UserResponseDTOMapper;
import com.personal.todo_app.models.user.User;
import com.personal.todo_app.repository.user.UserRepository;
import com.personal.todo_app.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<UserResponseDTO> getAllUsers() {
        return UserResponseDTOMapper.mapToUserResponseList(
                userRepository.findAll()
        );
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
}
