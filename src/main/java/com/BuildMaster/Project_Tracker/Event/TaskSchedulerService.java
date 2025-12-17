package com.BuildMaster.Project_Tracker.Event;

import com.BuildMaster.Project_Tracker.Entity.Task;
import com.BuildMaster.Project_Tracker.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskSchedulerService {

    private final TaskRepository taskRepository;
    private final ApplicationEventPublisher eventPublisher;

    // Run every day at 8AM
    @Scheduled(cron = "0 0 8 * * ?")
    public void checkOverdueTasks() {
        List<Task> tasks = taskRepository.findByStatusNotAndDueDateBefore("COMPLETED", LocalDate.now());
        for (Task task : tasks) {
            eventPublisher.publishEvent(new overdueTaskEvent(this,task));
        }
    }
}
