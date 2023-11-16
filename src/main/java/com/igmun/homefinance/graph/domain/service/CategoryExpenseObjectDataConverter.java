package com.igmun.homefinance.graph.domain.service;

import org.springframework.stereotype.Component;

@Component
public class CategoryExpenseObjectDataConverter extends ObjectDataConverter<CategoryExpenseDataExtractor> {
  public CategoryExpenseObjectDataConverter(CategoryExpenseDataExtractor rawDataExtractor) {
    super(rawDataExtractor);
  }
}
