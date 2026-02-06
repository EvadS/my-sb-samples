package com.se.sample.controller;


import com.se.sample.models.Broadcast;
import com.se.sample.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class MarketingController {

    private final TaskService taskService;

    @PostMapping("/broadcast/start")
    public ResponseEntity<Void> broadcast(){
        Broadcast op = new Broadcast();
        taskService.submit(op);

        String str = String.format("/tasks/%s",op.getTask().getId());
        return ResponseEntity
                .ok()
                .location(URI.create(str))
                .build();
    }
}
