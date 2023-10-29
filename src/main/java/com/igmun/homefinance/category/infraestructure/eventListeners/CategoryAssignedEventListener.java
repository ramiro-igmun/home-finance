package com.igmun.homefinance.category.infraestructure.eventListeners;

import com.igmun.homefinance.category.application.CreateCategoryService;
import com.igmun.homefinance.position.domain.event.CategoryAssignedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryAssignedEventListener {
  private final CreateCategoryService createCategoryService;

  @EventListener
  public void categoryAssigned(CategoryAssignedEvent categoryAssignedEvent) {
    createCategoryService.createCategory(categoryAssignedEvent.getData().tag());
  }
}
