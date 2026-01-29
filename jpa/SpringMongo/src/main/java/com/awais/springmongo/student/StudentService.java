package com.awais.springmongo.student;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Get all students
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentRepository.findAll());
    }

    // Get student by ID
    public ResponseEntity<Student> getStudentById(String id) {
        return studentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get student by email
    public ResponseEntity<Student> getStudentByEmail(String email) {
        return studentRepository.findStudentByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Add new student
    public ResponseEntity<Student> addStudent(Student student) {
        return ResponseEntity.ok(studentRepository.save(student));
    }

    // Update existing student
    public ResponseEntity<Student> updateStudent(String id, Student updatedStudent) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setFirstName(updatedStudent.getFirstName());
                    existingStudent.setLastName(updatedStudent.getLastName());
                    existingStudent.setGender(updatedStudent.getGender());
                    existingStudent.setEmail(updatedStudent.getEmail());
                    existingStudent.setAddress(updatedStudent.getAddress());
                    existingStudent.setFavouriteSubjects(updatedStudent.getFavouriteSubjects());
                    existingStudent.setTotalSpentOnBooks(updatedStudent.getTotalSpentOnBooks());
                    existingStudent.setCreatedDate(updatedStudent.getCreatedDate());

                    return ResponseEntity.ok(studentRepository.save(existingStudent));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete student by ID
    public ResponseEntity<Void> deleteStudent(String id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

