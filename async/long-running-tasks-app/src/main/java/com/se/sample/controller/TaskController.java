package com.se.sample.controller;

import com.se.sample.models.TaskRequest;
import com.se.sample.models.TaskResponse;
import com.se.sample.service.AsyncService;
import com.se.sample.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private AsyncService asyncService;

    @Autowired private TaskService taskService;

    @PostMapping("/create")
    TaskResponse createTask(@RequestBody TaskRequest task) {
        TaskResponse taskResponse =  taskService.createTask(task);
        return taskResponse;
    }

    @GetMapping("/list")
    List<String> getAllTasks() {
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    TaskResponse getById(  @PathVariable(name = "id")String id) {
        return taskService.getById(id);
    }



    @GetMapping("/run")
    public String runBackgroundTask() {
        asyncService.executeAsyncTask();
        return "Background task is running...";
    }
}