package com.BuildMaster.Project_Tracker.Service;

import com.BuildMaster.Project_Tracker.Entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;



public interface ProjectService {

    Project createProject(Project project);

    Project updateProject(Long id, Project project);

    void deleteProject(Long id);

    Project getProjectById(Long id);

    Page<Project> getAllProjects(int page, int size, String sortBy, String direction);

}
