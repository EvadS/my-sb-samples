package ua.se.sample.model;

import org.springframework.data.annotation.Id;

import java.util.List;

public class InstituteClass {

    @Id
    private String id;
    private String instituteId;
    private String classId;
    private String section;
    private short year;
    private List<String> studentIds;
    private String classTeacherId; //Homeroom Teacher
}
