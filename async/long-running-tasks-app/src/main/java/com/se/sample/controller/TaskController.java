package com.se.sample.controller;

import com.se.sample.models.response.TaskResponse;
import com.se.sample.service.AsyncService;
import com.se.sample.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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