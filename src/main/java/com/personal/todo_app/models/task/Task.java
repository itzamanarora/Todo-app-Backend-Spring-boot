package com.personal.todo_app.models.task;

import com.personal.todo_app.models.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name= "id", nullable = false, updatable = false)
    private UUID taskId;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private STATUSES status;

    @Enumerated(EnumType.STRING)
    private PRIORITIES priority;

    @Column(name = "display_order", nullable = false)
    @Builder.Default
    private int displayOrder = 0;

    @Column(name = "due_date")
    private Instant dueDate;

    @Column(name = "completed_at")
    private Instant completedAt;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name= "updated_at")
    private Instant updatedAt;

    @Builder.Default
    @Column(nullable = false)
    private boolean deleted = false;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;
}
