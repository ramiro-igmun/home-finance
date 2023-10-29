package com.igmun.homefinance.position.infrastructure.persistance;

import com.igmun.homefinance.position.domain.PositionRepository;
import com.igmun.homefinance.position.domain.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor
public class PositionJdbcRepositoryAdapter implements PositionRepository {
  private final PositionJdbcRepository jdbcRepository;
  private final PositionJdbcMapper jdbcMapper;

  @Override
  public void save(Position position) {
    jdbcRepository.save(jdbcMapper.fromPosition(position));
  }

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

  @Override
  public Optional<Position> findById(Long id) {
    return jdbcRepository.findById(id).map(jdbcMapper::toPosition);
  }

  @Override
  public List<Position> findByDescription(String description) {
    return jdbcRepository.findByDescription(description);
  }
}
