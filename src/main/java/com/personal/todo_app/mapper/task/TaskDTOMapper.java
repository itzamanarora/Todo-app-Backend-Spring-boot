package com.personal.todo_app.mapper.task;

import com.personal.todo_app.dto.task.TaskRequestDTO;
import com.personal.todo_app.dto.task.TaskResponseDTO;
import com.personal.todo_app.models.task.Task;

import java.util.List;

public class TaskDTOMapper {
    public static Task mapToTask(TaskRequestDTO taskRequestDTO) {
        return Task.builder()
                .title(taskRequestDTO.getTitle())
                .description(taskRequestDTO.getDescription())
                .status(taskRequestDTO.getStatus())
                .priority(taskRequestDTO.getPriority())
                .dueDate(taskRequestDTO.getDueDate())
                .build();
    }

    public static TaskResponseDTO mapToTaskResponse(Task task) {
        return TaskResponseDTO.builder()
                .taskId(task.getTaskId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .displayOrder(task.getDisplayOrder())
                .dueDate(task.getDueDate())
                .createdAt(task.getCreatedAt())
                .completedAt(task.getCompletedAt())
                .build();
    }

    public static List<TaskResponseDTO> mapToTaskList(List<Task> taskList) {
        return taskList.stream()
                .map(TaskDTOMapper::mapToTaskResponse)
                .toList();
    }
}
