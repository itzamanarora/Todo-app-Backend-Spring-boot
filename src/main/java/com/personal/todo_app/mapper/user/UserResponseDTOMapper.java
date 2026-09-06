package com.personal.todo_app.mapper.user;

import com.personal.todo_app.dto.user.UserResponseDTO;
import com.personal.todo_app.models.user.User;

import java.util.List;

public class UserResponseDTOMapper {
    public static UserResponseDTO mapToUserResponse(User user) {
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public static List<UserResponseDTO> mapToUserResponseList(List<User> users) {
        return users.stream()
                .map(UserResponseDTOMapper::mapToUserResponse)
                .toList();
    }
}
