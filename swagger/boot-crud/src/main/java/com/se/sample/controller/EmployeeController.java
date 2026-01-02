package com.se.sample.controller;


import com.se.sample.dto.EmployeeDto;
import com.se.sample.exception.EmployeeNotFoundException;
import com.se.sample.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/employee")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto createdEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @GetMapping(value = "/employee/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("employeeId") Long employeeId)
            throws EmployeeNotFoundException {
        try {
            EmployeeDto employee = employeeService.getEmployeeById(employeeId);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (EmployeeNotFoundException employeeNotFoundException) {
            throw employeeNotFoundException;
        }
    }

    @GetMapping(value = "/employees")
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        List<EmployeeDto> employees = employeeService.getEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @DeleteMapping(value = "/employee/{employeeId}")
    public ResponseEntity<HttpStatus> deleteEmployees(@PathVariable("employeeId") Long employeeId)
            throws EmployeeNotFoundException {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/employee")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto)
            throws EmployeeNotFoundException {
        EmployeeDto createdEmployee = employeeService.updateEmployee(employeeDto);
        return new ResponseEntity<>(createdEmployee, HttpStatus.OK);
    }
}
