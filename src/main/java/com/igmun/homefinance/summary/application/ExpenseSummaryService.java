package com.igmun.homefinance.summary.application;

import com.igmun.homefinance.category.domain.Category;
import com.igmun.homefinance.position.domain.Position;
import com.igmun.homefinance.position.domain.PositionRepository;
import com.igmun.homefinance.summary.domain.CategorySummary;
import com.igmun.homefinance.summary.domain.ExpensesSummary;
import com.igmun.homefinance.summary.domain.GroupSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseSummaryService {

  private final PositionRepository positionRepository;

  public ExpensesSummary expensesSummary(LocalDate start, LocalDate end) {
    List<Position> positions = positionRepository.findByDateBetween(start, end);
    Map<Category, BigDecimal> categoryMonthlyAverage = getMonthlyAverage(positions, Position::getCategory);
    Map<Category, BigDecimal> parentCategoryMonthlyAverage = getMonthlyAverage(positions, Position::getParentCategory);
    Map<Category, BigDecimal> totalByGroup = getTotal(positions, Position::getParentCategory);
    Map<Category, BigDecimal> totalByCategory = getTotal(positions, Position::getCategory);
    totalByGroup.keySet().stream().map(category -> {
      GroupSummary groupSummary = new GroupSummary();
    })
  }

  public Map<Category, BigDecimal> getMonthlyAverage(List<Position> positions, Function<Position, Category> groupingFunction) {
    return positions.stream()
      .collect(Collectors.groupingBy(groupingFunction,
        Collectors.collectingAndThen(
          Collectors.groupingBy(
            position -> YearMonth.from(position.getDate()),
            Collectors.summingDouble(position -> position.getAmount().doubleValue())
          ),
          monthlyTotals -> BigDecimal.valueOf(monthlyTotals.values().stream()
              .mapToDouble(Double::doubleValue).average().orElse(0.0))
            .setScale(2, RoundingMode.DOWN)
        )
      ));
  }

  public Map<Category, BigDecimal> getTotal(List<Position> positions, Function<Position, Category> groupingFunction) {
    return positions.stream()
      .collect(Collectors.groupingBy(groupingFunction,
        Collectors.reducing(BigDecimal.ZERO, Position::getAmount, BigDecimal::add)
      ));
  }
}
