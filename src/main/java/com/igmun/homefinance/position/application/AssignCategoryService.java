package com.igmun.homefinance.position.application;

import com.igmun.homefinance.category.domain.Category;
import com.igmun.homefinance.category.domain.CategoryRepository;
import com.igmun.homefinance.position.domain.PositionRepository;
import com.igmun.homefinance.position.domain.event.CategoryAssignedEvent;
import com.igmun.homefinance.shared.infraestructure.ApplicationEventBus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AssignCategoryService {
  private final PositionRepository positionRepository;
  private final CategoryRepository categoryRepository;
  private final ApplicationEventBus applicationEventBus;

  public void assignCategory(String description, String tag) {
    Category parent = categoryRepository.findParentCategoryByTag(tag)
      .orElseThrow(NoSuchElementException::new);
    positionRepository.findByDescriptionContaining(description).forEach(position -> {
      position.setCategory(new Category(tag));
      position.setParentCategory(parent);
      positionRepository.save(position);
    });
    applicationEventBus.publish(CategoryAssignedEvent.from(new Category(tag)));
  }

}
