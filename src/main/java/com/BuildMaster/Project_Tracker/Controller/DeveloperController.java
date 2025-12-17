package com.BuildMaster.Project_Tracker.Controller;


import com.BuildMaster.Project_Tracker.Entity.Developer;
import com.BuildMaster.Project_Tracker.Service.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/developers")

public class DeveloperController {

    private final DeveloperService developerService;

    @PostMapping
    public Developer createDeveloper(Developer developer){
        return developerService.createDeveloper(developer);
    }

    @PutMapping("/{id}")
    public Developer updateDeveloper(@PathVariable Long id, @RequestBody Developer developer){
        return developerService.updateDeveloper(id, developer);
    }

    @DeleteMapping("/{id}")
    public void deleteDeveloper(@PathVariable Long id){
        developerService.deleteDeveloper(id);
    }

    @GetMapping("/{id}")
    public Developer getDeveloperById(@PathVariable Long id){
        return developerService.getDeveloperById(id);
    }

    @GetMapping
    public Page<Developer> getAllDevelopers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return developerService.getAllDevelopers(page, size, sortBy, direction);
    }
}
