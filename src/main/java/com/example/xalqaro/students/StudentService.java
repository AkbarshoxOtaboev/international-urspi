package com.example.xalqaro.students;

import java.util.List;

public interface StudentService {
    void addStudent(StudentDTO studentDTO);
    List<Student> getStudents();
    Student getStudent(Long id);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    void changeStudentStatus(Long id, Integer status);
}
