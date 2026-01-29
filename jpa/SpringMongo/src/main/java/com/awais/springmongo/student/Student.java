package com.awais.springmongo.student;

import com.awais.springmongo.enums.Gender;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
public class Student {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Gender gender;
    @Indexed(unique = true)
    private String email;
    private Address address;
    private List<String> favouriteSubjects;
    private BigDecimal totalSpentOnBooks;
    private LocalDateTime createdDate;

    public Student(String firstName, String lastName, Gender gender, String email, Address address,
                   List<String> favouriteSubjects, BigDecimal totalSpentOnBooks, LocalDateTime createdDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.favouriteSubjects = favouriteSubjects;
        this.totalSpentOnBooks = totalSpentOnBooks;
        this.createdDate = createdDate;
    }
}
