package com.se.sample.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// This class contains the scheduled task to be executed at fixed intervals.
@Component
public class ScheduledTask {

    // This method runs every 5 seconds.
    @Scheduled(fixedRate = 5000)
    public void runTask() {
        System.out.println("Scheduled task running every 5 seconds");
    }
}
