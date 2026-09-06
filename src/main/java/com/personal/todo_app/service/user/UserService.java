package com.personal.todo_app.service.user;

import com.personal.todo_app.dto.user.UserResponseDTO;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> getAllUsers();
}
