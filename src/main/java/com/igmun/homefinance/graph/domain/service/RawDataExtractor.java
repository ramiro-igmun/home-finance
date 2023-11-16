package com.igmun.homefinance.graph.domain.service;

import com.igmun.homefinance.graph.domain.RawData;
import com.igmun.homefinance.position.domain.Position;

import java.util.List;

@FunctionalInterface
public interface RawDataExtractor {
  RawData extractData(List<Position> positions);
}
