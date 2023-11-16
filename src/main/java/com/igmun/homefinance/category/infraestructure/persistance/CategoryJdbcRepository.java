package com.igmun.homefinance.category.infraestructure.persistance;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryJdbcRepository extends CrudRepository<CategoryJdbc, Long> {
  Optional<CategoryJdbc> findByTagIgnoreCase(String tag);
  @Modifying
  @Query("DELETE FROM CATEGORY WHERE CATEGORY.tag = :tag")
  void deleteByTag(@Param("tag") String tag);
}
