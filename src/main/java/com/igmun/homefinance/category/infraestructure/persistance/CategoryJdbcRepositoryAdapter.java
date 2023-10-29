package com.igmun.homefinance.category.infraestructure.persistance;

import com.igmun.homefinance.category.domain.Category;
import com.igmun.homefinance.category.domain.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryJdbcRepositoryAdapter implements CategoryRepository {

  private final CategoryJdbcRepository categoryJdbcRepository;
  private final CategoryJdbcMapper mapper;

  @Override
  public Optional<Category> findByTag(String tag) {
    return categoryJdbcRepository.findByTagIgnoreCase(tag).map(mapper::toCategory);
  }

  @Override
  public void save(Category category) {
    categoryJdbcRepository.save(mapper.fromCategory(category));
  }

  @Override
  public void delete(String tag) {
    categoryJdbcRepository.deleteByTagIgnoreCase(tag);
  }
}
