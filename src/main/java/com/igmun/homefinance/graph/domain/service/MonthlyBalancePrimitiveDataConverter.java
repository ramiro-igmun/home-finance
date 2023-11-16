package com.igmun.homefinance.graph.domain.service;

import com.igmun.homefinance.graph.domain.PrimitiveData;
import com.igmun.homefinance.position.domain.Position;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthlyBalancePrimitiveDataConverter extends PrimitiveDataConverter<MonthlyBalanceDataExtractor> {

  public MonthlyBalancePrimitiveDataConverter(MonthlyBalanceDataExtractor rawDataExtractor) {
    super(rawDataExtractor);
  }

  @Override
  public PrimitiveData getData(List<Position> positions) {
    return super.getData(positions);
//        .addDataSet(getAccumulatedValue(positions), "ACCUMULATED", "line");
  }
}
