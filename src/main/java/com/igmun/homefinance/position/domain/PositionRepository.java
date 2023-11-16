package com.igmun.homefinance.position.domain;

import com.igmun.homefinance.category.domain.Category;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PositionRepository {
  void save(Position position);
  void saveAll(Collection<Position> positions);
  List<Position> getAll();
  Optional<Position> findById(Long id);
  List<Position> findByDescription(String description);
  List<Position> findByDescriptionContaining(String description);
  List<Position> findByCategory(Category category);
  List<Position> findByDateBetween(LocalDate beginDate, LocalDate endDate);
  Position getOldestPosition();
  Position getNewestPosition();
}
