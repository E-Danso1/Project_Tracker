package com.BuildMaster.Project_Tracker.Service.Implementations;


import com.BuildMaster.Project_Tracker.Entity.AuditLog;
import com.BuildMaster.Project_Tracker.Service.AuditLogService;
import com.BuildMaster.Project_Tracker.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AuditLogServiceImp implements AuditLogService {

    private final AuditLogRepository auditLogRepository;


    public List<AuditLog> getLogs(String entityType, String actor) {

        if (entityType != null && actor != null) {
            return auditLogRepository.findByEntityTypeAndActorName(entityType, actor);
        }
        if (entityType != null) {
            return auditLogRepository.findByEntityType(entityType);
        }
        if (actor != null) {
            return auditLogRepository.findByActorName(actor);
        }
        return auditLogRepository.findAll();
    }
}
