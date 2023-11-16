package com.igmun.homefinance.position.application;

import com.igmun.homefinance.position.domain.Position;
import com.igmun.homefinance.position.domain.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FetchPositionsService {
  private final PositionRepository positionRepository;

  public List<Position> retrievePositions(LocalDate beginDate, LocalDate endDate) {
    return positionRepository.findByDateBetween(beginDate, endDate);
  }
}
