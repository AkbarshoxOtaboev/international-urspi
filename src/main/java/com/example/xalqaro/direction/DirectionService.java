package com.example.xalqaro.direction;

import java.util.List;

public interface DirectionService {
    void save(Direction direction);
    void delete(Long id, Integer status);
    Direction findById(Long id);
    List<Direction> findAll();
    void update(Direction direction, Long id);
}
