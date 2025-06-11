package com.mewebstudio.springbootquartzimpl.controller;

import com.mewebstudio.springbootquartzimpl.dto.request.CreateTaskRequest;
import com.mewebstudio.springbootquartzimpl.dto.response.TaskResponse;
import com.mewebstudio.springbootquartzimpl.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody @Valid final CreateTaskRequest request) {
        return new ResponseEntity<>(TaskResponse.convert(taskService.create(request)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final String id) {
        taskService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> get(@PathVariable("id") final String id) {
        taskService.get(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
