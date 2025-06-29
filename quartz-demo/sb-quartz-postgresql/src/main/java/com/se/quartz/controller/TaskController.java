package com.se.quartz.controller;

import com.se.quartz.dto.request.CreateTaskRequest;
import com.se.quartz.dto.response.TaskResponse;
import com.se.quartz.entity.Task;
import com.se.quartz.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody @Valid final CreateTaskRequest request) {
        return new ResponseEntity<>(TaskResponse.convert(taskService.create(request)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> get(@PathVariable("id") final String id) {
        Task byId = taskService.getById(id);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final String id) {
        taskService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/list")
    public ResponseEntity<  List<Task>> list() {
        List<Task> list = taskService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
