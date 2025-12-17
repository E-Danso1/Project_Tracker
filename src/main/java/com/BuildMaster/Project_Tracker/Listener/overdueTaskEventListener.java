package com.BuildMaster.Project_Tracker.Listener;

import com.BuildMaster.Project_Tracker.Entity.Task;
import com.BuildMaster.Project_Tracker.Event.overdueTaskEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class overdueTaskEventListener {

    private final JavaMailSender mailSender;

    @EventListener
    public void handleTaskOverdue(overdueTaskEvent event) {
        Task task = event.getTask();

        // Create email message
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(task.getDeveloper().getEmail());
        message.setSubject("Task Overdue: " + task.getTitle());
        message.setText("Hello " + task.getDeveloper().getName() +
                ",\n\nYour task \"" + task.getTitle() +
                "\" was due on " + task.getDueDate() +
                ". Please take immediate action.\n\nRegards,\nProject Tracker");

        mailSender.send(message);
    }

}
