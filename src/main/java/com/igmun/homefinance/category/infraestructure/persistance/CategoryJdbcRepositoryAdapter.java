package com.igmun.homefinance.category.infraestructure.persistance;

import com.igmun.homefinance.category.domain.Category;
import com.igmun.homefinance.category.domain.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.StreamSupport;

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
  public List<Category> findByGroupTag(String tag) {
    return categoryJdbcRepository.findByGroupTag(tag).stream().map(mapper::toCategory).toList();
  }

  @Override
  public Optional<Category> findParentCategoryByTag(String tag) {
    return categoryJdbcRepository.findParentCategory(tag).map(mapper::toCategory);
  }

  @Override
  public List<Category> getAll() {
    return StreamSupport.stream(categoryJdbcRepository.findAll().spliterator(), false)
      .map(mapper::toCategory).toList();
  }

  @Override
  public List<Category> getAllGroups() {
    return categoryJdbcRepository.findAllGroups().stream()
      .map(mapper::toCategory).toList();
  }

  @Override
  public void save(Category category) {
    categoryJdbcRepository.save(mapper.fromCategory(category));
  }

  @Override
  public void delete(Category category) {
    CategoryJdbc categoryJdbc = categoryJdbcRepository.findByTagIgnoreCase(category.getTag()).orElseThrow(NoSuchElementException::new);
    categoryJdbcRepository.delete(categoryJdbc);
  }
}
