package com.igmun.homefinance.graph.domain.service;

import com.igmun.homefinance.category.domain.Category;
import com.igmun.homefinance.category.domain.CategoryRepository;
import com.igmun.homefinance.graph.domain.ObjectData;
import com.igmun.homefinance.position.domain.Position;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CategoryExpenseObjectDataConverter extends ObjectDataConverter<CategoryExpenseDataExtractor> {
  private final CategoryRepository categoryRepository;

  public CategoryExpenseObjectDataConverter(CategoryExpenseDataExtractor rawDataExtractor, CategoryRepository categoryRepository) {
    super(rawDataExtractor);
    this.categoryRepository = categoryRepository;
  }

  @Override
  public ObjectData getData(List<Position> positions) {
    Map<String, String> categoryColorMap = categoryRepository.getAll().stream()
      .collect(Collectors.toMap(Category::getTag, Category::getColor));

    ObjectData data = super.getData(positions);
    return new ObjectData(data.datasets().stream()
      .map(set -> new ObjectData.Dataset(
        set.data(),
        set.label().isBlank() ? "OTHERS" : set.label(),
   "bar",
        Optional.ofNullable(categoryColorMap.get(set.label())).orElse("gray")))
      .sorted(Comparator.comparing(ObjectData.Dataset::backgroundColor))
      .toList());
  }
}
