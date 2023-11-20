package com.igmun.homefinance.position.infrastructure.persistance;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PositionJdbcRepository extends CrudRepository<PositionJdbc, Long> {
  List<PositionJdbc> findByDescription(String description);
  List<PositionJdbc> findByDescriptionContaining(String description);
  @Query("select * from POSITION where CATEGORY = :category or PARENT_CATEGORY = :category")
  List<PositionJdbc> findByCategory(String category);
  @Query("select * from POSITION p where p.DATE <= :endDate and p.DATE >= :beginDate order by DATE")
  List<PositionJdbc> findByDateBetween(@Param("beginDate") LocalDate beginDate, @Param("endDate") LocalDate endDate);
  @Query("select top 1 * from POSITION order by DATE")
  PositionJdbc getOldestPosition();
  @Query("select top 1 * from POSITION order by DATE desc")
  PositionJdbc getNewestPosition();
}
