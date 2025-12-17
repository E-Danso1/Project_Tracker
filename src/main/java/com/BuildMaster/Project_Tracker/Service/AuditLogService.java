package com.BuildMaster.Project_Tracker.Service;

import com.BuildMaster.Project_Tracker.Entity.AuditLog;

import java.util.List;

public interface AuditLogService {

    List<AuditLog> getLogs(String entity, String actor);
}
