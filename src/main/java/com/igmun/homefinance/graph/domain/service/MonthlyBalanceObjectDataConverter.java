package com.igmun.homefinance.graph.domain.service;

import com.igmun.homefinance.graph.domain.ObjectData;
import com.igmun.homefinance.graph.domain.PrimitiveData;
import com.igmun.homefinance.position.domain.Position;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.igmun.homefinance.position.domain.Positions.getAccumulatedValue;

@Service
public class MonthlyBalanceObjectDataConverter extends ObjectDataConverter<MonthlyBalanceDataExtractor> {

  public MonthlyBalanceObjectDataConverter(MonthlyBalanceDataExtractor rawDataExtractor) {
    super(rawDataExtractor);
  }

  @Override
  public ObjectData getData(List<Position> positions) {
    return super.getData(positions);
  }
}
