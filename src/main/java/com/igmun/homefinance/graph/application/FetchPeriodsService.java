package com.igmun.homefinance.graph.application;

import com.igmun.homefinance.graph.domain.TimePeriod;
import com.igmun.homefinance.position.domain.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FetchPeriodsService {
  private final PositionRepository positionRepository;

  public List<TimePeriod> fetchPeriods() {
    LocalDate beginDate = positionRepository.getOldestPosition().getDate();
    LocalDate endDate = positionRepository.getNewestPosition().getDate();
    return getTimePeriods(beginDate, endDate);
  }

  private static ArrayList<TimePeriod> getTimePeriods(LocalDate beginDate, LocalDate endDate) {
    ArrayList<TimePeriod> timePeriods = new ArrayList<>();
    Period period = Period.between(beginDate, endDate);
    long months = period.toTotalMonths() + (period.getDays() > 0 ? 1 : 0);
    int yearMonths = beginDate.getMonthValue();
    int year = beginDate.getYear();
    TimePeriod yearPeriod = getYearPeriod(year);
    timePeriods.add(yearPeriod);
    for (int i = 1; i <= months; i++) {
      if (yearMonths >= 13) {
        yearMonths = 1;
        timePeriods.add(getYearPeriod(++year));
      }
      Month month = Month.of(yearMonths);
      YearMonth yearMonth = YearMonth.of(year, yearMonths);
      TimePeriod timePeriod = new TimePeriod(
        year + " " + month.toString(),
        yearMonth.atDay(1).toString(),
        yearMonth.atEndOfMonth().toString());
      timePeriods.add(timePeriod);
      yearMonths++;
    }
    return timePeriods;
  }

  private static TimePeriod getYearPeriod(int year) {
    LocalDate startOfYear = Year.of(year).atMonthDay(MonthDay.of(1, 1));
    LocalDate endOfYear = Year.of(year).atMonthDay(MonthDay.of(12, 31));
    return new TimePeriod(String.valueOf(year), startOfYear.toString(), endOfYear.toString());
  }
}
