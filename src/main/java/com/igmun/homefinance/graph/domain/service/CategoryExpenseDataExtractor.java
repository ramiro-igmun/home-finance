package com.igmun.homefinance.graph.domain.service;

import com.igmun.homefinance.graph.domain.RawData;
import com.igmun.homefinance.position.domain.Position;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryExpenseDataExtractor implements RawDataExtractor{

  @Override
  public RawData extractData(List<Position> positions) {
    return new RawData(positions.stream()
      .filter(position -> Position.Type.EXPENSE == position.getType())
      .collect(Collectors.groupingBy(position -> position.getCategory().getTag(),
        Collectors.groupingBy(position -> YearMonth.of(position.getDate().getYear(), position.getDate().getMonth()).atDay(1).toString(), LinkedHashMap::new,
          Collectors.summingDouble(value -> value.getAmount().abs().doubleValue())))));
  }
}
