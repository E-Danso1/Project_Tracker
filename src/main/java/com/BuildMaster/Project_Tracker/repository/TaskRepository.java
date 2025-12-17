package com.BuildMaster.Project_Tracker.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.BuildMaster.Project_Tracker.Entity.Task;
import com.BuildMaster.Project_Tracker.repository.projection.DeveloperTaskCount;
import com.BuildMaster.Project_Tracker.repository.projection.StatusCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProjectId(Long ProjectId);


    List<Task> findByDeveloper_Id(Long developerId);


    // OverDue tasks (Not Done)
    @Query("SELECT t FROM Task t WHERE t.dueDate < :today AND t.status <> 'DONE'")
    List<Task> findOverdueTasks(@Param("today") LocalDate today);


    // Top Developers with the most tasks Assigned (Use Projection)
    @Query("""
    SELECT t.developer AS developer, COUNT(t) AS count
    FROM Task t
    GROUP BY t.developer
    ORDER BY COUNT(t) DESC
""")
    List<DeveloperTaskCount> findTopDevelopersWithTaskCounts(Pageable pageable);


    // Get task counts grouped by status (Use Projection)
    @Query(
            "SELECT t.status As status," +
                    "COUNT(t) As count " +
                    "FROM Task t " +
                    "GROUP BY t.status"
    )
    List<StatusCount> CountTasksGroupedByStatus();


    Page<Task> findAll(Pageable pageable);


    List<Task> findByStatusNotAndDueDateBefore(String status, LocalDate date);
}

