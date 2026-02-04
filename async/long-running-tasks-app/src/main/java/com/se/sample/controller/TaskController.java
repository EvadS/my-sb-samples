package com.se.sample.controller;

import com.se.sample.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/run")
    public String runBackgroundTask() {
        asyncService.executeAsyncTask();
        return "Background task is running...";
    }
}