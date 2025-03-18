package com.example.xalqaro.direction;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectionServiceImplement implements DirectionService {
    private final DirectionRepository directionRepository;
    @Override
    public void save(Direction direction) {
        direction.setStatus(1);
        directionRepository.save(direction);
    }

    @Override
    public void delete(Long id, Integer status) {
        Direction direction = findById(id);
        direction.setStatus(status.equals(1) ? 0 : 1);
        directionRepository.saveAndFlush(direction);
    }

    @Override
    public Direction findById(Long id) {
        return directionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Direction> findAll() {
        return directionRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public void update(Direction direction, Long id) {
        Direction directionToUpdate = findById(id);
        directionToUpdate.setName(direction.getName());
        directionRepository.saveAndFlush(directionToUpdate);
    }
}
