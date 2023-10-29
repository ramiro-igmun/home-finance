package com.igmun.homefinance.position.application;

import com.igmun.homefinance.category.domain.Category;
import com.igmun.homefinance.position.domain.PositionRepository;
import com.igmun.homefinance.position.domain.event.CategoryAssignedEvent;
import com.igmun.homefinance.shared.infraestructure.ApplicationEventBus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssignCategoryService {
  private final PositionRepository positionRepository;
  private final ApplicationEventBus applicationEventBus;

  public void assignCategory(String description, String tag) {
    positionRepository.findByDescription(description).forEach(position -> {
      position.setCategory(new Category(tag));
      positionRepository.save(position);
    });
    applicationEventBus.publish(CategoryAssignedEvent.from(new Category(tag)));
  }

}
