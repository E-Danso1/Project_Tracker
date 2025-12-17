package com.BuildMaster.Project_Tracker.Event;

import com.BuildMaster.Project_Tracker.Entity.Task;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class overdueTaskEvent extends ApplicationEvent {

    private final Task task;

    public overdueTaskEvent(Object source, Task task) {
        super(source);
        this.task = task;
    }

}
