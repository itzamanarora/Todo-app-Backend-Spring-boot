package com.personal.todo_app.dto.task;

import com.personal.todo_app.models.task.PRIORITIES;
import com.personal.todo_app.models.task.STATUSES;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskUpdateRequestDTO {

        @Size(max = 255, message = "Title can not be exceed 255 characters.")
        private String title;
        private String description;
        private STATUSES status;
        private PRIORITIES priority;
        private Instant dueDate;
        private Integer displayOrder;
        private Instant completedAt;

}
