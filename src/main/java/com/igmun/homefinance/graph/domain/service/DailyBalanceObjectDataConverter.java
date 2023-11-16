package com.igmun.homefinance.graph.domain.service;

import com.igmun.homefinance.graph.domain.ObjectData;
import com.igmun.homefinance.position.domain.Position;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyBalanceObjectDataConverter extends ObjectDataConverter<DailyBalanceDataExtractor> {

  public DailyBalanceObjectDataConverter(DailyBalanceDataExtractor rawDataExtractor) {
    super(rawDataExtractor);
  }

  @Override
  public ObjectData getData(List<Position> positions) {
    return super.getData(positions);
  }
}
