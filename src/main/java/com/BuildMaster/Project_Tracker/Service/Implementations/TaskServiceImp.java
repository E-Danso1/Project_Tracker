package com.BuildMaster.Project_Tracker.Service.Implementations;

import com.BuildMaster.Project_Tracker.Entity.AuditLog;
import com.BuildMaster.Project_Tracker.Entity.Developer;
import com.BuildMaster.Project_Tracker.Entity.Project;
import com.BuildMaster.Project_Tracker.Entity.Task;
import com.BuildMaster.Project_Tracker.Service.TaskService;
import com.BuildMaster.Project_Tracker.repository.AuditLogRepository;
import com.BuildMaster.Project_Tracker.repository.DeveloperRepository;
import com.BuildMaster.Project_Tracker.repository.ProjectRepository;
import com.BuildMaster.Project_Tracker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImp implements TaskService {

    private final TaskRepository taskRepository;
    private final AuditLogRepository auditLogRepository;
    private final DeveloperRepository developerRepository;
    private final ProjectRepository projectRepository;

    private void logAudit(String actionType, String entityType, String entityId, String actor, String payload ) {
        AuditLog log = AuditLog.builder()
                .actionType(actionType)
                .actorName(actor)
                .entityId(entityId)
                .entityType(entityType)
                .payload(payload)
                .timestamp(LocalDateTime.now())
                .build();
        auditLogRepository.save(log);
    }

    @Override
    @Transactional
    public Task createTask(Task task, Long projectId, Long developerId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(()->new RuntimeException("Project not found"));

        Developer developer = developerRepository.findById(developerId)
                .orElseThrow(()->new RuntimeException("Developer not found"));

        task.setProject(project);
        task.setDeveloper(developer);

        Task savedTask = taskRepository.save(task);

        logAudit("CREATE", "Task", savedTask.getId().toString(), "SYSTEM", "{}");
        return savedTask;
    }


    @Override
    @Transactional
    public Task updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Task not found"));

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());
        task.setDueDate(updatedTask.getDueDate());

        Task saved = taskRepository.save(task);

        logAudit("UPDATE", "Task", saved.getId().toString(), "SYSTEM", "{}");
        return saved;
    }


    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
        logAudit("DELETE", "Task", id.toString(), "SYSTEM", "{}");
    }

    @Override
    public  List<Task> getTasksByProject(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }


    @Override
    public List<Task> getAllTasksByDeveloper(Long developerId) {
        return taskRepository.findByDeveloper_Id(developerId);
    }

    @Override
    public List<Task> getOverdueTasks() {
        return taskRepository.findOverdueTasks(LocalDate.now());
    }

    @Override
    public Page<Task> getAllTasks(int page, int size, String sortBy, String direction){

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        PageRequest pageable = PageRequest.of(page, size, sort);
        return taskRepository.findAll(pageable);
    }

    @Transactional
    public Task assignTaskToDeveloper(Long taskId, Long developerId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(()->new RuntimeException("Task not found"));
        Developer developer = developerRepository.findById(developerId)
                .orElseThrow();
        task.setDeveloper(developer);
        Task saved = taskRepository.save(task);

        logAudit("UPDATE", "Task", saved.getId().toString(), "SYSTEM", saved.toString());
        return saved;
    }

}
