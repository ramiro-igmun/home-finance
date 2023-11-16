package com.igmun.homefinance.position.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Positions {

  private Positions() {}

  public static List<Position> densifyPositions(List<Position> positions) {
    LocalDate beginDate = positions.get(0).getDate();
    LocalDate endDate = positions.get(positions.size() - 1).getDate();
    List<Position> basePositions = Stream.iterate(beginDate, date -> date.plusDays(1))
      .limit(ChronoUnit.DAYS.between(beginDate, endDate) + 1)
      .flatMap(date -> Stream.of(
        Position.createPosition(date, new BigDecimal("-0.00000001"), ""),
        Position.createPosition(date, BigDecimal.ZERO, ""))
      ).toList();
    return Stream.concat(basePositions.stream(), positions.stream()).sorted(Comparator.comparing(Position::getDate)).toList();
  }

  public static List<BigDecimal> getAccumulatedValue(List<Position> positions) {
    return positions.stream()
      .collect(Collectors.groupingBy(position -> position.getDate().toString(), LinkedHashMap::new,
        Collectors.summingDouble(value -> value.getAmount().doubleValue()))).values().stream()
      .reduce(new ArrayList<>(), (acc, val) -> {
        BigDecimal value = !acc.isEmpty() ? acc.get(acc.size() - 1) : BigDecimal.ZERO;
        acc.add(value.add(BigDecimal.valueOf(val)));
        return acc;
      }, (val1, val2) -> val1);
  }
}
