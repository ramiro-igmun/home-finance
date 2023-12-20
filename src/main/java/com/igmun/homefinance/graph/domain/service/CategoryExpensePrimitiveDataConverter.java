package com.igmun.homefinance.graph.domain.service;

import com.igmun.homefinance.category.domain.Category;
import com.igmun.homefinance.category.domain.CategoryRepository;
import com.igmun.homefinance.graph.domain.ObjectData;
import com.igmun.homefinance.graph.domain.PrimitiveData;
import com.igmun.homefinance.position.domain.Position;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CategoryExpensePrimitiveDataConverter extends PrimitiveDataConverter<CategoryExpenseDataExtractor> {
  public CategoryExpensePrimitiveDataConverter(CategoryExpenseDataExtractor rawDataExtractor) {
    super(rawDataExtractor);
  }

}
