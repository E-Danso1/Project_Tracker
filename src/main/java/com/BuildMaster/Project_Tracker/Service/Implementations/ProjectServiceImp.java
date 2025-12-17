package com.BuildMaster.Project_Tracker.Service.Implementations;

import com.BuildMaster.Project_Tracker.Entity.AuditLog;
import com.BuildMaster.Project_Tracker.Entity.Project;
import com.BuildMaster.Project_Tracker.Service.ProjectService;
import com.BuildMaster.Project_Tracker.repository.AuditLogRepository;
import com.BuildMaster.Project_Tracker.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImp implements ProjectService {

    private final ProjectRepository projectRepository;
    private final AuditLogRepository auditLogRepository;


    private void logAudit(String actionType, String entityType, String entityId, String actor, String payload) {
        AuditLog log = AuditLog.builder()
                .actionType(actionType)
                .entityType(entityType)
                .entityId(entityId)
                .actorName(actor)
                .payload(payload)
                .timestamp(LocalDateTime.now())
                .build();
        auditLogRepository.save(log);
    }

    @Override
    @Transactional
    public Project createProject(Project project) {
        Project saved = projectRepository.save(project);

        logAudit("CREATE", "Project", saved.getId().toString(), "SYSTEM", saved.toString());
        return saved;
    }

    @Override
    @Transactional
    @Cacheable(value = "projects", key = "#project.id")
    public Project updateProject(Long id, Project project) {
        Project existing = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project Not Found"));
        existing.setName(project.getName());
        existing.setDescription(project.getDescription());
        existing.setDeadline(project.getDeadline());
        existing.setStatus(project.getStatus());
        Project saved = projectRepository.save(project);

        logAudit("UPDATE", "project", saved.getId().toString(), "SYSTEM", saved.toString());
        return saved;
    }

    @Override
    @Transactional
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);

        logAudit("DELETE", "project", id.toString(), "SYSTEM", "{}");
    }

    @Override
    @Cacheable(value = "projects", key = "#id")
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project Not Found"));
    }

    @Override
    @Cacheable(value = "developers", key = "all")
    public Page<Project> getAllProjects(int page, int size, String sortBy, String direction){
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return projectRepository.findAll(pageable);
    }
}
