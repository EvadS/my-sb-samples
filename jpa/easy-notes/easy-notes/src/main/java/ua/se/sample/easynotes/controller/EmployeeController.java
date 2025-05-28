package ua.se.sample.easynotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.se.sample.easynotes.dto.EmployeeDto;
import ua.se.sample.easynotes.exception.EmployeeNotFoundException;
import ua.se.sample.easynotes.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto createdEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        List<EmployeeDto> employees = employeeService.getEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }


    @GetMapping(value = "/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("employeeId") Long employeeId)
            throws EmployeeNotFoundException {
        try {
            EmployeeDto employee = employeeService.getEmployeeById(employeeId);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (EmployeeNotFoundException employeeNotFoundException) {
            throw employeeNotFoundException;
        }
    }



    @DeleteMapping(value = "/{employeeId}")
    public ResponseEntity<HttpStatus> deleteEmployees(@PathVariable("employeeId") Long employeeId)
            throws EmployeeNotFoundException {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto)
            throws EmployeeNotFoundException {
        EmployeeDto createdEmployee = employeeService.updateEmployee(employeeDto);
        return new ResponseEntity<>(createdEmployee, HttpStatus.OK);
    }

}