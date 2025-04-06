package com.se.sample.controller;


import com.se.sample.models.Employee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/hire")
@Api(value = "enum", description = "Document Enum in Swagger")
public class HireController {


    @ApiOperation(value = "This method is used to hire employee with a specific role")
    @ResponseBody
    @PostMapping
    public String hireEmployee(@ApiParam(value = "role", required = true) @RequestBody @Valid  Employee employee) {
        return String.format("Hired for role: %s", employee.role.name());
    }
}