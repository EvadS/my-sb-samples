package ua.se.sample.easynotes.mapper;


import ua.se.sample.easynotes.dto.EmployeeDto;
import ua.se.sample.easynotes.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        return new EmployeeDto(employee.getId(), employee.getName(), employee.getDepartment());
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        return new Employee(employeeDto.getId(), employeeDto.getName(), employeeDto.getDepartment());
    }
}
