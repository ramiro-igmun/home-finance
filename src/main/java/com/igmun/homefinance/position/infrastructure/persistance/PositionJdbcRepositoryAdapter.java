package com.igmun.homefinance.position.infrastructure.persistance;

import com.igmun.homefinance.category.domain.Category;
import com.igmun.homefinance.position.domain.PositionRepository;
import com.igmun.homefinance.position.domain.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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
    return jdbcRepository.findByDescription(description).stream().map(jdbcMapper::toPosition).toList();
  }

  @Override
  public List<Position> findByDescriptionContaining(String description) {
    return jdbcRepository.findByDescriptionContaining(description).stream().map(jdbcMapper::toPosition).toList();
  }

  @Override
  public List<Position> findByCategory(Category category) {
    return jdbcRepository.findByCategory(category.getTag()).stream().map(jdbcMapper::toPosition).toList();
  }

  @Override
  public List<Position> findByDateBetween(LocalDate beginDate, LocalDate endDate) {
    return jdbcRepository.findByDateBetween(beginDate, endDate).stream().map(jdbcMapper::toPosition).toList();
  }

  @Override
  public Position getOldestPosition() {
    return jdbcMapper.toPosition(jdbcRepository.getOldestPosition());
  }

  @Override
  public Position getNewestPosition() {
    return jdbcMapper.toPosition(jdbcRepository.getNewestPosition());
  }
}
