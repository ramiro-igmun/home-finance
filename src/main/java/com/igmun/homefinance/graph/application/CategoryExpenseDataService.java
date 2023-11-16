package com.igmun.homefinance.graph.application;

import com.igmun.homefinance.graph.domain.ObjectData;
import com.igmun.homefinance.graph.domain.service.CategoryExpenseObjectDataConverter;
import com.igmun.homefinance.position.domain.Position;
import com.igmun.homefinance.position.domain.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryExpenseDataService {

  private final CategoryExpenseObjectDataConverter dataExtractor;
  private final PositionRepository positionRepository;

  public ObjectData getData(LocalDate beginDate, LocalDate endDate) {
    List<Position> positions = positionRepository.findByDateBetween(beginDate, endDate);
    return dataExtractor.getData(positions);
  }

}
