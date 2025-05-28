package ua.se.sample.easynotes.service;


import ua.se.sample.easynotes.dto.EmployeeDto;
import ua.se.sample.easynotes.exception.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long employeeId) throws EmployeeNotFoundException;
    List<EmployeeDto> getEmployees();
    void deleteEmployee(Long employeeId) throws EmployeeNotFoundException;
    EmployeeDto updateEmployee(EmployeeDto employeeDto) throws EmployeeNotFoundException;

}
