package com.igmun.homefinance.category.domain;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository  {
  Optional<Category> findByTag(String tag);
  List<Category> getAllCategories();
  void save(Category category);
  void delete(String tag);
}
