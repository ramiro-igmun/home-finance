package com.igmun.homefinance.category.domain;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository  {
  Optional<Category> findByTag(String tag);
  List<Category> findByGroupTag(String tag);
  Optional<Category> findParentCategoryByTag(String tag);
  List<Category> getAll();
  List<Category> getAllGroups();
  void save(Category category);
  void delete(Category category);
}
