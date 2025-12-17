package com.BuildMaster.Project_Tracker.Service;

import com.BuildMaster.Project_Tracker.Entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;

import java.util.List;


public interface TaskService {

    Task createTask (Task task, Long projectId, Long developerId);

    Task updateTask (Long id, Task task);

    void deleteTask (Long id);

    List<Task> getTasksByProject(Long ProjectId);

    List<Task> getAllTasksByDeveloper(Long DeveloperId);

    List<Task> getOverdueTasks();

    Page<Task> getAllTasks(int page, int size, String sortBy, String direction);
    
}
