package com.igmun.homefinance.position.infrastructure.persistance;

import com.igmun.homefinance.position.domain.PositionRepository;
import com.igmun.homefinance.position.domain.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class PositionJdbcRepositoryAdapter implements PositionRepository {
  private final PositionJdbcRepository jdbcRepository;
  private final PositionJdbcMapper jdbcMapper;

  @Override
  public void saveAll(Collection<Position> positions) {
    List<PositionJdbc> jdbcPositions = positions.stream().map(jdbcMapper::fromPosition).toList();
    jdbcRepository.saveAll(jdbcPositions);
  }

  @Override
  public List<Position> getAll() {
    Iterable<PositionJdbc> jdbcPositions = jdbcRepository.findAll();
    return StreamSupport.stream(jdbcPositions.spliterator(), false).map(jdbcMapper::toPosition).toList();
  }
}
