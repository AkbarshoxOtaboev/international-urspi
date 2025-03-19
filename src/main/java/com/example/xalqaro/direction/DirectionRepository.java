package com.example.xalqaro.direction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
    @Query(value = "SELECT * FROM directions WHERE status = 1", nativeQuery = true)
    List<Direction> fetchActiveDirection();
}
