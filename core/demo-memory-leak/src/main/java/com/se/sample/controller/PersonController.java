package com.se.sample.controller;

import com.se.sample.memoryleaks.equalshashcode.BadPerson;
import com.se.sample.memoryleaks.equalshashcode.Person;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;


@Tag(name = "Equals and Hashcode", description = "Base scenario to show equal and hash code usage")
@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private Map<Person, String> personStringMap = new HashMap<>();
    private Map<BadPerson, String> badPersonStringWeakHashMap = new WeakHashMap<>();
    private Map<BadPerson, String> badPersonStringMap = new HashMap<>();


    @Operation(
            summary = "1. object WITHOUT equals and hash code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation completed"),
    })
    @GetMapping("improperEqualsAndHash")
    public void someBadMapEntries(){
        for (int i = 0; i < 1_000_000; i++) {
            badPersonStringMap.put(new BadPerson("FirstName","LastName"), i+"_person_added");
        }
    }

    @Operation(
            summary = "object WITHOUT equals and hash code, WEAKHASHMAP")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation completed"),
    })
    @GetMapping("improperEqualsAndHashV2")
    public void someBadMapEntriesWithWeakHashMap(){
        for (int i = 0; i < 1_000_000; i++) {
            badPersonStringWeakHashMap.put(new BadPerson("FirstName","LastName"), i+"_person_added");
        }
    }

    @Operation(
            summary = "object WITH equals and hash code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation completed"),
    })
    @GetMapping("properEqualsAndHash")
    public void someMapEntries(){
        for (int i = 0; i < 1_000_000; i++) {
            personStringMap.put(new Person("FirstName", "LastName"), i+"_person_added");
        }
    }

    @GetMapping("personCache")
    public Map<Person, String> getCache(){
        return personStringMap;
    }

    @GetMapping("personLeakingWeakCache")
    public Map<BadPerson, String> fetchCache(){
        return badPersonStringWeakHashMap;
    }

    @GetMapping("personLeakingCache")
    public Map<BadPerson, String> fetchLeakingCache(){
        return badPersonStringMap;
    }
}