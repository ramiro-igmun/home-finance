package com.igmun.homefinance.position.infrastructure.eventListeners;

import com.igmun.homefinance.category.domain.Category;
import com.igmun.homefinance.category.domain.event.CategoryDeletedEvent;
import com.igmun.homefinance.position.application.UnAssignCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryDeletedEventListener {

  private final UnAssignCategoryService unAssignCategoryService;

  @EventListener
  public void onCategoryDeleted(CategoryDeletedEvent categoryDeletedEvent) {
    Category category = Category.fromTag(categoryDeletedEvent.getData().tag());
    unAssignCategoryService.unAssignCategory(category);
  }
}
