package com.personal.todo_app.controller.task;

import com.personal.todo_app.dto.task.TaskRequestDTO;
import com.personal.todo_app.dto.task.TaskResponseDTO;
import com.personal.todo_app.dto.task.TaskUpdateRequestDTO;
import com.personal.todo_app.service.task.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/task")
@Tag(name="Task API Endpoints")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks(Authentication authentication) {
        UUID userId = (UUID) authentication.getPrincipal();
        return ResponseEntity.ok(taskService.getAllTask(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO taskRequestDTO, Authentication authentication) {
        UUID userId = (UUID) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.createTask(taskRequestDTO, userId));
    }

    @PatchMapping("/update/{taskId}")
    public ResponseEntity<TaskResponseDTO> updateTask(@RequestBody TaskUpdateRequestDTO taskUpdateRequestDTO, @PathVariable UUID taskId, Authentication authentication) {
        UUID userId = (UUID) authentication.getPrincipal();
        return ResponseEntity.ok(taskService.updateTask(taskId, userId, taskUpdateRequestDTO));
    }

    @DeleteMapping("/delete/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId, Authentication authentication) {
        UUID userId = (UUID) authentication.getPrincipal();
        taskService.deleteTask(taskId, userId);
        return ResponseEntity.noContent().build();
    }
}
