package com.igmun.homefinance.graph.domain.service;

import com.igmun.homefinance.graph.domain.RawData;
import com.igmun.homefinance.position.domain.Position;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.igmun.homefinance.position.domain.Positions.densifyPositions;

@Component
public class DailyBalanceDataExtractor implements RawDataExtractor {
  @Override
  public RawData extractData(List<Position> positions) {
      return new RawData(positions.stream()
        .collect(Collectors.groupingBy(position -> position.getType().toString(),
          Collectors.groupingBy(position -> position.getDate().toString(), LinkedHashMap::new,
            Collectors.summingDouble(value -> value.getAmount().abs().doubleValue())))));
    }

}
