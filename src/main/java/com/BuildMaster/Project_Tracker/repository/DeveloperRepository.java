package com.BuildMaster.Project_Tracker.repository;

import com.BuildMaster.Project_Tracker.Entity.Developer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {

    @Query("""
    SELECT d
    FROM Developer d
    ORDER BY size(d.tasks) DESC
""")
    List<Developer> findTop5ByTasksCount();

    Page<Developer> findAll(Pageable pageable);
}

