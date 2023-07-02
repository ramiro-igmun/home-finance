package com.igmun.homefinance.position.domain;

import com.igmun.homefinance.position.domain.Position;

import java.util.Collection;
import java.util.List;

public interface PositionRepository {
  void saveAll(Collection<Position> positions);
  List<Position> getAll();
}
