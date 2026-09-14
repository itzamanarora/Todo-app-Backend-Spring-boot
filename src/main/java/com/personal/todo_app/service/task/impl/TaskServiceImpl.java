package com.personal.todo_app.service.task.impl;

import com.personal.todo_app.dto.task.TaskRequestDTO;
import com.personal.todo_app.dto.task.TaskResponseDTO;
import com.personal.todo_app.dto.task.TaskUpdateRequestDTO;
import com.personal.todo_app.exception.task.TaskNotFoundException;
import com.personal.todo_app.mapper.task.TaskDTOMapper;
import com.personal.todo_app.models.task.STATUSES;
import com.personal.todo_app.models.task.Task;
import com.personal.todo_app.models.user.User;
import com.personal.todo_app.repository.task.TaskRepository;
import com.personal.todo_app.repository.user.UserRepository;
import com.personal.todo_app.service.task.TaskService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<TaskResponseDTO> getAllTask(UUID userId) {
        log.info("Getting All task of user: {}", userId);
        List<Task> task = taskRepository.findAllByUser_UserId(userId);
        return TaskDTOMapper.mapToTaskList(task);
    }

    @Override
    @Transactional
    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO, UUID userId) {
        Task task = TaskDTOMapper.mapToTask(taskRequestDTO);
        User user = userRepository.findByUserId(userId).orElseThrow(() ->
                new UsernameNotFoundException("User not found")
        );
        task.setUser(user);
        Task savedTask = taskRepository.save(task);
        log.info("Task created successfully: {}", savedTask.getTaskId());
        return TaskDTOMapper.mapToTaskResponse(savedTask);
    }

    @Override
    @Transactional
    public TaskResponseDTO updateTask(UUID taskId, UUID userId, TaskUpdateRequestDTO taskUpdateRequestDTO) {
        Task task = taskRepository.findByTaskIdAndUser_UserIdAndDeletedFalse(taskId, userId).orElseThrow(() ->
                new TaskNotFoundException("Task not found"));

        if (taskUpdateRequestDTO.getTitle() != null) task.setTitle(taskUpdateRequestDTO.getTitle());
        if (taskUpdateRequestDTO.getDescription() != null) task.setDescription(taskUpdateRequestDTO.getDescription());
        if (taskUpdateRequestDTO.getPriority() != null) task.setPriority(taskUpdateRequestDTO.getPriority());
        if (taskUpdateRequestDTO.getDueDate() != null) task.setDueDate(taskUpdateRequestDTO.getDueDate());
        if (taskUpdateRequestDTO.getDisplayOrder() != null) task.setDisplayOrder(taskUpdateRequestDTO.getDisplayOrder());
        if (taskUpdateRequestDTO.getStatus() != null) {
            task.setStatus(taskUpdateRequestDTO.getStatus());
            if(taskUpdateRequestDTO.getStatus() == STATUSES.COMPLETE) task.setCompletedAt(Instant.now());
            else task.setCompletedAt(null);
        }

        Task savedTask = taskRepository.save(task);
        log.info("Task updated successfully on task id: {} of the user id: {}", taskId, userId);

        return TaskDTOMapper.mapToTaskResponse(savedTask);
    }

    @Override
    public void deleteTask(UUID taskId, UUID userId) {
        Task task = taskRepository.findByTaskIdAndUser_UserIdAndDeletedFalse(taskId, userId).orElseThrow(() ->
                new TaskNotFoundException("Task not found."));
        task.setDeleted(true);
        taskRepository.save(task);
    }
}
