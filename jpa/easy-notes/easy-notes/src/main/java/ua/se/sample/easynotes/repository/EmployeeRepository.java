package ua.se.sample.easynotes.repository;


import ua.se.sample.easynotes.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}