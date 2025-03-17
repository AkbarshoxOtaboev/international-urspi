package com.example.xalqaro.students;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    @Query(value = "select (select COUNT(*) FROM students WHERE status = 1 ) as status_1, (select count(*) from students where status=2) as status_2", nativeQuery = true)
    Integer countByActiveStatus();
}
