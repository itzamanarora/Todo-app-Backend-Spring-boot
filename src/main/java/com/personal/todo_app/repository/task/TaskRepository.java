package com.personal.todo_app.repository.task;

import com.personal.todo_app.models.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findAllByUser_UserIdAndDeletedFalse(UUID userId);
    Optional<Task> findByTaskIdAndUser_UserIdAndDeletedFalse(UUID taskId, UUID userId);
    Optional<Task> findByTaskId(UUID taskId);
}
