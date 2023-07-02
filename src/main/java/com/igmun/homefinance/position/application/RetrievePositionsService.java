package com.igmun.homefinance.position.application;

import com.igmun.homefinance.position.domain.Position;
import com.igmun.homefinance.position.domain.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RetrievePositionsService {
  private final PositionRepository positionRepository;

  public List<Position> retrievePositions() {
    return positionRepository.getAll();
  }
}
