package com.igmun.homefinance.category.application;

import com.igmun.homefinance.category.domain.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCategoryService {

  private final CategoryRepository categoryRepository;

  public void deleteCategory(String tag) {
    categoryRepository.delete(tag);
  }
}
