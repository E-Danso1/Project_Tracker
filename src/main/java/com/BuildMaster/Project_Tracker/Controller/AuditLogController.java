package com.BuildMaster.Project_Tracker.Controller;


import com.BuildMaster.Project_Tracker.Entity.AuditLog;
import com.BuildMaster.Project_Tracker.Service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/logs")

public class AuditLogController {

    private final AuditLogService auditLogService;

    @GetMapping
    public List<AuditLog> getLogs(
            @RequestParam(required = false) String entityType,
            @RequestParam(required = false) String actor) {
        return auditLogService.getLogs(entityType, actor);
    }
}
