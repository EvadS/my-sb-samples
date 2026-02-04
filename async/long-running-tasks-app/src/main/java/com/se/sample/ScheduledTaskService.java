package com.se.sample;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTaskService {

    // runs a task every 10 seconds, logging the current time to the console.
    @Scheduled(fixedRate = 10000)
    public void performScheduledTask() {
        System.out.println("Scheduled task executed at " + System.currentTimeMillis());
    }
}
