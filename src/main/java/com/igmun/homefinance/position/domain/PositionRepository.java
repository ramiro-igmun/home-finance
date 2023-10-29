package com.igmun.homefinance.position.domain;

import com.igmun.homefinance.position.domain.Position;
import com.igmun.homefinance.position.infrastructure.persistance.PositionJdbc;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PositionRepository {
  void save(Position position);
  void saveAll(Collection<Position> positions);
  List<Position> getAll();
  Optional<Position> findById(Long id);
  List<Position> findByDescription(String description);
}
