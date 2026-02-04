package com.se.sample.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Async("taskExecutor")
    public void executeAsyncTask() {
        System.out.println("Execute method asynchronously - " + Thread.currentThread().getName());
        try {
            // Simulating a long-running task
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task execution completed");
    }
}
