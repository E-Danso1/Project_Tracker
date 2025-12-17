package com.BuildMaster.Project_Tracker.Controller;

import com.BuildMaster.Project_Tracker.Entity.Project;
import com.BuildMaster.Project_Tracker.Service.Implementations.ProjectServiceImp;
import com.BuildMaster.Project_Tracker.Service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/projects")

public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public Project createProject(@RequestBody Project project){
        return projectService.createProject(project);
    }


    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project project){
        return projectService.updateProject(id, project);
    }


    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
    }


    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id){
        return projectService.getProjectById(id);
    }

    @GetMapping
    public Page<Project> getAllProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return projectService.getAllProjects(page, size, sortBy, direction);
    }

}
