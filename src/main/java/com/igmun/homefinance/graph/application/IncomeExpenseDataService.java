package com.igmun.homefinance.graph.application;

import com.igmun.homefinance.graph.domain.ObjectData;
import com.igmun.homefinance.graph.domain.PrimitiveData;
import com.igmun.homefinance.graph.domain.service.DailyBalanceObjectDataConverter;
import com.igmun.homefinance.graph.domain.service.DailyBalancePrimitiveDataConverter;
import com.igmun.homefinance.graph.domain.service.MonthlyBalanceObjectDataConverter;
import com.igmun.homefinance.graph.domain.service.MonthlyBalancePrimitiveDataConverter;
import com.igmun.homefinance.position.domain.Position;
import com.igmun.homefinance.position.domain.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeExpenseDataService {

  private final PositionRepository positionRepository;
  private final MonthlyBalanceObjectDataConverter monthlyBalanceObjectDataConverter;
  private final DailyBalanceObjectDataConverter dailyBalanceObjectDataConverter;

  public ObjectData getData(LocalDate begin, LocalDate end) {
    List<Position> positions = positionRepository.findByDateBetween(begin, end);
    if (begin.getMonthValue() != end.getMonthValue()) {
      return monthlyBalanceObjectDataConverter.getData(positions);
    }
    return dailyBalanceObjectDataConverter.getData(positions);
  }
}
