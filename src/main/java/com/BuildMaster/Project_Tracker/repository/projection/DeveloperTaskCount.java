package com.BuildMaster.Project_Tracker.repository.projection;

import com.BuildMaster.Project_Tracker.Entity.Developer;

public interface DeveloperTaskCount {
    Developer getDeveloper();
    Long getCount();
}
