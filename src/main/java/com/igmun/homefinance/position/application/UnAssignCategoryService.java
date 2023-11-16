package com.igmun.homefinance.position.application;

import com.igmun.homefinance.category.domain.Category;
import com.igmun.homefinance.position.domain.Position;
import com.igmun.homefinance.position.domain.PositionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UnAssignCategoryService {

  private final PositionRepository positionRepository;

  public void unAssignCategory(Category category) {
    List<Position> positions = positionRepository.findByCategory(category);
    positions.forEach(Position::unAssignCategory);
    positionRepository.saveAll(positions);
  }
}
