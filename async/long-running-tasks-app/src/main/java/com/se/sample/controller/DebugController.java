package com.se.sample.controller;

import com.se.sample.config.ControllersApiPaths;
import com.se.sample.models.GenerationRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;


@Log4j2
@RestController
@RequestMapping(ControllersApiPaths.BASE_PATH + ControllersApiPaths.REPORTS_API_PATH)
public class DebugController {

    private final static String implementationVersion = "0.0.1";

    @GetMapping(ControllersApiPaths.GENERATE_API)
    public Map<String, String> getVersion() {

        Map<String, String> result = new HashMap<>();
        result.put("version", implementationVersion);
        return result;
    }


//    @PostMapping(ControllersApiPaths.GENERATE_API)
//    public GenerateResultSummaryResponse generateResultSummary(
//           @Valid @NotNull @RequestBody GenerationRequest generationRequest) {
//        return summaryBySummaryService.resultingSummaryProcessing(summaryRequest);
//    }

}
