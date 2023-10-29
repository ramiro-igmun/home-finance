package com.igmun.homefinance.category.infraestructure.persistance;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryJdbcRepository extends CrudRepository<CategoryJdbc, Long> {
  Optional<CategoryJdbc> findByTagIgnoreCase(String tag);
  void deleteByTagIgnoreCase(String tag);
}
