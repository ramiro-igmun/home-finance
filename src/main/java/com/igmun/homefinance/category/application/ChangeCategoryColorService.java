package com.igmun.homefinance.category.application;

import com.igmun.homefinance.category.domain.Category;
import com.igmun.homefinance.category.domain.CategoryRepository;
import com.igmun.homefinance.shared.domain.Colors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChangeCategoryColorService {

  private final CategoryRepository categoryRepository;

  public void changeCategoryColor(String tag, String color) {
    categoryRepository.findByTag(tag).ifPresent(category -> {
      category.setColor(color);
      int i = 1;
      for (Category subCategory : category.getSubCategories()) {
        Color subColor = Colors.brightenColor(Color.decode(color), i * 0.03);
        subCategory.setColor(Colors.colorToHexString(subColor));
        categoryRepository.save(subCategory);
        i++;
      }
      categoryRepository.save(category);
    });
  }
}
