package com.example.xalqaro.students;

import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    @Query(value = "select COUNT(*) FROM students WHERE status = 1", nativeQuery = true)
    Integer countByActiveStatus();
    @Query(value = "select " +
            "(select COUNT(*) from students where status = 1) as statusOne, " +
            "(select COUNT(*) from students where status = 2) as statusTwo, " +
            "(select COUNT(*) from students where status = 3) as statusThree, " +
            "(select COUNT(*) from students where status = 4) as statusFour",
            nativeQuery = true)
    List<Object[]> countsStudentStatusRaw();
}
