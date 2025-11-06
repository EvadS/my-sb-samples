package com.se.sample.controller;


import com.se.sample.memoryleaks.equalshashcode.BadPerson;
import com.se.sample.memoryleaks.equalshashcode.Person;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Memory Check", description = "Base scenario to show memory leaks")
@RestController
@RequestMapping("/api/memory")
public class MemoryCheckController {

    public List<Double> notStaticList = new ArrayList<>();
    public static List<Double> staticList = new ArrayList<>();


    @GetMapping("/static-list")
    @Operation(
            summary = "Check memory usage with static list",
            description = "Check memory usage with static list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation completed"),
    })
    @ResponseBody
    public ResponseEntity<Void>   checkMemoryUsageForStatic() {
        populateStaticList();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/non-static-list")
    @Operation(
            summary = "Check memory usage with non-static list",
            description = "Check memory usage with non-static list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation completed"),
    })
    @ResponseBody
    public ResponseEntity<Void>  checkMemoryUsageForNonStaticList() {
        populateNonStaticList();
        return ResponseEntity.ok().build();
    }

    //-------------------------------------------------------------------------------------
    @GetMapping("/without-equals-hashcode")
    @Operation(
            summary = "Check memory usage in classes without own equals and hash code",
            description = "Not overridden methods for the equals() and hashCode() methods n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation completed"),
    })
    @ResponseBody
    public ResponseEntity<Map<String, Integer>>  checkMemoryUsageObjectWithoutEqualsAndHashMap() {

        int iterationNumber = 10000000;
        Map<BadPerson, Integer> map = new HashMap<BadPerson, Integer>();
        for(int i=0; i<10000000; i++) {
            map.put(new BadPerson("Jon", "Doe"), 1);
        }

        System.out.print("Debug Point - VisuaLVM");
        Map<String, Integer> responseMap = new HashMap<>();
        responseMap.put("iterationNumber", iterationNumber);
        responseMap.put("mapSize", map.size());

        return ResponseEntity.ok(responseMap);
    }

    @GetMapping("/with-equals-hashcode")
    @Operation(
            summary = "Check memory usage in classes with own equals and hash code",
            description = "the equals() and hashCode() override")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation completed"),
    })
    @ResponseBody
    public ResponseEntity<Map<String, Integer>> checkMemoryUsageObjectWithEqualsAndHashMap() {

        int iterationNumber = 10000;
        Map<Person, Integer> map = new HashMap<Person, Integer>();
        for(int i=0; i<10000; i++) {
            map.put(new Person("John", "Doe"), 1);
        }

        System.out.print("Debug Point - VisuaLVM");
        Map<String, Integer> responseMap = new HashMap<>();
        responseMap.put("iterationNumber", iterationNumber);
        responseMap.put("mapSize", map.size());

        return ResponseEntity.ok(responseMap);
    }

    public void populateNonStaticList() {
        for (int i = 0; i < 10000000; i++) {
            notStaticList.add(Math.random());
        }
        System.out.println("Debug Point 2");
    }

    public void populateStaticList() {
        for (int i = 0; i < 10000000; i++) {
            staticList.add(Math.random());
        }
        System.out.println("Debug Point 2");
    }



}
