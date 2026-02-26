package com.se.sample.controller;


import com.se.sample.model.DocumentHistory;
import com.se.sample.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/history")
@RequiredArgsConstructor
@Validated

public class HistoryController {

    private final HistoryService historyService;
    @ResponseBody
    @GetMapping("/list")
    public List<DocumentHistory> getHistoryByEmail() {
        return historyService.getAll();
    }
}
