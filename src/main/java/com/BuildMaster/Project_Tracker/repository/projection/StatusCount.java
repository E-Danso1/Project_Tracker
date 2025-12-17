package com.BuildMaster.Project_Tracker.repository.projection;

import com.BuildMaster.Project_Tracker.Entity.TaskStatus;

public interface StatusCount {
    String getStatus();
    Long getCount();

}
