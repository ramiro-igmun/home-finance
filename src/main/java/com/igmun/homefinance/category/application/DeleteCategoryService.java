package com.igmun.homefinance.category.application;

import com.igmun.homefinance.category.domain.Category;
import com.igmun.homefinance.category.domain.CategoryRepository;
import com.igmun.homefinance.category.domain.event.CategoryDeletedEvent;
import com.igmun.homefinance.shared.event.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCategoryService {

  private final CategoryRepository categoryRepository;
  private final DomainEventPublisher domainEventPublisher;

  public void deleteCategory(String tag) {
    categoryRepository.delete(new Category(tag));
    domainEventPublisher.publish(CategoryDeletedEvent.from(new Category(tag)));
  }
}
