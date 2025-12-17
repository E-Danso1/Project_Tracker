package com.BuildMaster.Project_Tracker.Service;

import com.BuildMaster.Project_Tracker.Entity.Developer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface DeveloperService {

    Developer createDeveloper (Developer developer);

    Developer updateDeveloper (Long id, Developer developer);

    void deleteDeveloper (Long id);

    Developer getDeveloperById(Long id);

    List<Developer> getAllDevelopers();

    List<Developer> getTopDevelopers();

    Page<Developer> getAllDevelopers(int page, int size, String sortBy, String direction);
}
