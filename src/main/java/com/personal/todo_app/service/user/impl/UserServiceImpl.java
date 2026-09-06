package com.personal.todo_app.service.user.impl;

import com.personal.todo_app.dto.user.UserResponseDTO;
import com.personal.todo_app.mapper.user.UserResponseDTOMapper;
import com.personal.todo_app.repository.user.UserRepository;
import com.personal.todo_app.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return UserResponseDTOMapper.mapToUserResponseList(
                userRepository.findAll()
        );
    }
}
