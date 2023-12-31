package com.igmun.homefinance.category.infraestructure.persistance;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryJdbcRepository extends CrudRepository<CategoryJdbc, Long> {
  Optional<CategoryJdbc> findByTagIgnoreCase(String tag);
  @Query("SELECT * FROM CATEGORY p JOIN CATEGORY c ON p.ID = c.PARENT_CATEGORY_ID WHERE c.TAG = :tag")
  Optional<CategoryJdbc> findParentCategory(@Param("tag") String tag);
  @Modifying
  @Query("DELETE FROM CATEGORY WHERE CATEGORY.tag = :tag")
  void deleteByTag(@Param("tag") String tag);
  @Query("SELECT * FROM CATEGORY WHERE PARENT_CATEGORY_ID is null")
  List<CategoryJdbc> findAllGroups();
  @Query("SELECT * FROM CATEGORY c JOIN CATEGORY p ON p.ID = c.PARENT_CATEGORY_ID WHERE p.TAG = :tag")
  List<CategoryJdbc> findByGroupTag(@Param("tag") String tag);
}
