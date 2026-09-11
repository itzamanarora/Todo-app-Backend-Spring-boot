package com.personal.todo_app.service.task;

import com.personal.todo_app.dto.task.TaskRequestDTO;
import com.personal.todo_app.dto.task.TaskResponseDTO;
import com.personal.todo_app.dto.task.TaskUpdateRequestDTO;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<TaskResponseDTO> getAllTask(UUID userId);
    TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO, UUID userId);
    TaskResponseDTO updateTask(UUID taskId, UUID userId, TaskUpdateRequestDTO taskUpdateRequestDTO);
    void deleteTask(UUID taskId, UUID userId);
}
