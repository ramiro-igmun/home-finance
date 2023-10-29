package com.igmun.homefinance.position.infrastructure.persistance;

import com.igmun.homefinance.position.domain.Position;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PositionJdbcRepository extends CrudRepository<PositionJdbc, Long> {
  List<Position> findByDescription(String description);
}
