package com.igmun.homefinance.category.application;

import com.igmun.homefinance.category.domain.Category;
import com.igmun.homefinance.category.domain.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AddSubCategoryService {

  private final CategoryRepository categoryRepository;

  public void addSubCategory(String parentTag, String childTag) {
    Category category = categoryRepository.findByTag(parentTag)
      .orElseThrow(NoSuchElementException::new);
    category.addSubCategory(childTag);
    categoryRepository.save(category);
  }

}
