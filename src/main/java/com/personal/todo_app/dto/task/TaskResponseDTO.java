package com.personal.todo_app.dto.task;

import com.personal.todo_app.models.task.PRIORITIES;
import com.personal.todo_app.models.task.STATUSES;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponseDTO {
    private UUID taskId;
    private String title;
    private String description;
    private STATUSES status;
    private PRIORITIES priority;
    private int displayOrder;
    private Instant dueDate;
    private Instant completedAt;
    private Instant createdAt;
}
