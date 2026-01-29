package com.awais.springmongo.services;

import com.awais.springmongo.enums.Gender;
import com.awais.springmongo.student.Address;
import com.awais.springmongo.student.Student;
import com.awais.springmongo.student.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class DataGenerationService {

    private final StudentRepository studentRepository;
    private final QueryTest queryTest;

    @Value("${data.generation.enabled}")
    private boolean dataGenerationEnabled;

    public DataGenerationService(StudentRepository studentRepository, QueryTest queryTest) {
        this.studentRepository = studentRepository;
        this.queryTest = queryTest;
    }

    @PostConstruct
    public void init() {
        System.out.println("Data Generation Enabled: " + dataGenerationEnabled);  // Check if property is injected
        if (dataGenerationEnabled) {
            generateData();
        } else {
            System.out.println("Data generation is disabled.");
        }
    }

    private void generateData() {
        // Creating student objects
        Student student1 = new Student(
                "John",
                "Doe",
                Gender.MALE,
                "john.doe@example.com",
                new Address("123 Maple St", "Fairfield", "IA", "USA", "52556"),
                List.of("Math", "Science"),
                new BigDecimal("150.75"),
                LocalDateTime.now()
        );

        Student student2 = new Student(
                "Alice",
                "Smith",
                Gender.FEMALE,
                "alice.smith@example.com",
                new Address("456 Elm St", "Chicago", "IL", "USA", "60616"),
                List.of("History", "English", "Art"),
                new BigDecimal("230.50"),
                LocalDateTime.now()
        );

        Student student3 = new Student(
                "Bob",
                "Johnson",
                Gender.MALE,
                "bob.johnson@example.com",
                new Address("789 Oak Ave", "Austin", "TX", "USA", "73301"),
                List.of("Physics", "Chemistry"),
                new BigDecimal("175.00"),
                LocalDateTime.now()
        );

        Student student4 = new Student(
                "Emily",
                "Davis",
                Gender.FEMALE,
                "emily.davis@example.com",
                new Address("321 Pine Rd", "Seattle", "WA", "USA", "98101"),
                List.of("Biology", "Computer Science"),
                new BigDecimal("300.20"),
                LocalDateTime.now()
        );

        Student student5 = new Student(
                "Michael",
                "Brown",
                Gender.MALE,
                "michael.brown@example.com",
                new Address("654 Cedar Ln", "New York", "NY", "USA", "10001"),
                List.of("Economics", "Statistics"),
                new BigDecimal("120.00"),
                LocalDateTime.now()
        );

        // Save to repository
        System.out.println("\n\nAdding students..");
        List<Student> students = Arrays.asList(student1, student2, student3, student4, student5);
        studentRepository.saveAll(students);

        // Query test: Find student by email
        System.out.println("\n\nFind Student By Query:");
        List<Student> students1 = queryTest.findByEmail(student1.getEmail());
        System.out.println(students1);

        // Repository test: Find student by email
        System.out.println("\n\nFind Student By Repository:");
        studentRepository.findStudentByEmail(student1.getEmail()).ifPresentOrElse(System.out::println, () -> {
            System.out.println("Student Not Found");
        });
    }
}
