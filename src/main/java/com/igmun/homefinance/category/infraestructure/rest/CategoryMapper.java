package com.igmun.homefinance.category.infraestructure.rest;

import com.igmun.homefinance.category.domain.Category;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

  public CategoryDto fromCategory(Category category) {
    return new CategoryDto(category.getTag(), category.getSubCategories().stream()
      .map(Category::getTag).collect(Collectors.toSet()));
  }

  public List<CategoryDto> fromCategoryList(Collection<Category> categories) {
    return categories.stream()
      .map(this::fromCategory)
      .toList();
  }
}
