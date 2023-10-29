package com.igmun.homefinance.category.application;

import com.igmun.homefinance.category.domain.Category;
import com.igmun.homefinance.category.domain.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCategoryService {

  private final CategoryRepository categoryRepository;

  public void createCategory(String tag) {
    if (categoryRepository.findByTag(tag).isEmpty()) {
      categoryRepository.save(new Category(tag));
    }
  }

}
