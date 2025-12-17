package com.BuildMaster.Project_Tracker.Controller;


import com.BuildMaster.Project_Tracker.Entity.Task;
import com.BuildMaster.Project_Tracker.Service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/tasks")

public class TaskController {

    private final TaskService taskService;

    @PostMapping("/project/{projectId}/developer/{developerId}")
    public Task createTask(
            @RequestBody Task task,
            @PathVariable Long projectId,
            @PathVariable Long developerId
    ){
        return taskService.createTask(task, projectId, developerId);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task){
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id){
        taskService.deleteTask(id);
    }

    @GetMapping("/project/{projectId}")
    public List<Task> getTasksByProject(@PathVariable long projectId){
        return taskService.getTasksByProject(projectId);
    }

    @GetMapping("/developer/{developerId}")
    public List<Task> getAllTasksByDeveloper(@PathVariable long developerId){
        return taskService.getAllTasksByDeveloper(developerId);
    }

    @GetMapping("/overdue")
    public List<Task> getOverdueTask(){
        return taskService.getOverdueTasks();
    }

    @GetMapping
    public Page<Task> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "dueDate") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return taskService.getAllTasks(page, size, sortBy, direction);
    }
}
