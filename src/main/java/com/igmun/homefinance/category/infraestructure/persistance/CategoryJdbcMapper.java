package com.igmun.homefinance.category.infraestructure.persistance;

import com.igmun.homefinance.category.domain.Category;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryJdbcMapper {
  CategoryJdbc fromCategory(Category category) {
    return new CategoryJdbc(category.getId(), category.getTag(), category.getSubCategories().stream()
      .map(subCategory -> new CategoryJdbc(subCategory.getId(), subCategory.getTag())).collect(Collectors.toSet()));
  }

  Category toCategory(CategoryJdbc categoryJdbc) {
    return new Category(categoryJdbc.getId(), categoryJdbc.getTag(), categoryJdbc.getSubCategories()
      .stream().map(subCategory -> new Category(subCategory.getTag())).collect(Collectors.toSet()));
  }
}
