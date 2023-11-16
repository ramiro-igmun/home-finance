package com.igmun.homefinance.graph.domain.service;

import com.igmun.homefinance.graph.domain.PrimitiveData;
import com.igmun.homefinance.position.domain.Position;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.igmun.homefinance.position.domain.Positions.getAccumulatedValue;

@Service
public class DailyBalancePrimitiveDataConverter extends PrimitiveDataConverter<DailyBalanceDataExtractor> {
  public DailyBalancePrimitiveDataConverter(DailyBalanceDataExtractor rawDataExtractor) {
    super(rawDataExtractor);
  }

  @Override
  public PrimitiveData getData(List<Position> positions) {
    return super.getData(positions)
        .addDataSet(new PrimitiveData.Dataset(getAccumulatedValue(positions), "ACCUMULATED", "line"));
  }
}
