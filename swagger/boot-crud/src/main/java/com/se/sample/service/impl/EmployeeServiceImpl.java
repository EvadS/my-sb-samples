package com.se.sample.service.impl;

import com.se.sample.dto.EmployeeDto;
import com.se.sample.entity.Employee;
import com.se.sample.exception.EmployeeNotFoundException;
import com.se.sample.mapper.EmployeeMapper;
import com.se.sample.repository.EmployeeRepository;
import com.se.sample.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee createdEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(createdEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee with id - " + employeeId + " not found.");
        }
        return EmployeeMapper.mapToEmployeeDto(employee.get());
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((emp) -> EmployeeMapper.mapToEmployeeDto(emp)).collect(Collectors.toList());
    }

    @Override
    public void deleteEmployee(Long employeeId) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee with id - " + employeeId + " not found.");
        }
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) throws EmployeeNotFoundException {
        Optional<Employee> retrievedEmployee = employeeRepository.findById(employeeDto.getId());
        if (retrievedEmployee.isEmpty()) {
            throw new EmployeeNotFoundException("Employee with id - " + employeeDto.getId() + " not found.");
        }
        Employee employee = retrievedEmployee.get();
        employee.setName(employeeDto.getName());
        employee.setDepartment(employeeDto.getDepartment());
        Employee createdEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(createdEmployee);
    }
}