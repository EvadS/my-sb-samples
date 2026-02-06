package com.se.sample.controller;

import com.se.sample.models.TaskRequest;
import com.se.sample.models.TaskResponse;
import com.se.sample.service.AsyncService;
import com.se.sample.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private AsyncService asyncService;

    @Autowired private TaskServiceImpl taskService;

    @GetMapping("/list")
    List<String> getAllTasks() {
        return taskService.getAll();
    }


    @GetMapping("/{id}")
    TaskResponse get(@PathVariable String id ){
        return taskService.getById(id);
    }
}