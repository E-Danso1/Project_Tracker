package com.BuildMaster.Project_Tracker.repository;

import com.BuildMaster.Project_Tracker.Entity.AuditLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogRepository extends MongoRepository<AuditLog, String> {

    // Fetch Logs by entity type
    List<AuditLog> findByEntityType(String entityType);

    // Fetch logs by actor name
    List<AuditLog> findByActorName(String actorName);

    List<AuditLog> findByEntityTypeAndActorName(String entity, String actorName);

}
