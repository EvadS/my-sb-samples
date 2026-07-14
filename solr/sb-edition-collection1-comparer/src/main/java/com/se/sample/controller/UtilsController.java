package com.se.sample.controller;

import com.se.sample.service.ComparisonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/utils")
public class UtilsController {

    private final ComparisonService comparisonService;

    public UtilsController(ComparisonService comparisonService) {
        this.comparisonService = comparisonService;
    }

    @ResponseBody
    @GetMapping("/lost-editions-list")
    public List<String> lostEditions(){
        return comparisonService.getLostEditionsList("");
    }

}
