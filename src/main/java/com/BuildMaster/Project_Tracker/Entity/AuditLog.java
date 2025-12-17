package com.BuildMaster.Project_Tracker.Entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "audit_logs")

public class AuditLog {

    @Id
    private Long id;

    private String actionType; // CREATE, UPDATE, DELETE

    private String entityType; // Project, Task, Developer

    private String entityId;

    private LocalDateTime timestamp;

    private String actorName; // "System" or logged-in user

    private String payload;  // JSON Snapshot of entity

}
