package com.igmun.homefinance.category.infraestructure.persistance;

import com.igmun.homefinance.category.domain.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryJdbcMapper {
  CategoryJdbc fromCategory(Category category) {
    return new CategoryJdbc(null, category.getTag());
  }

  Category toCategory(CategoryJdbc categoryJdbc) {
    return new Category(categoryJdbc.getTag());
  }
}
