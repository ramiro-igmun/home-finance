package com.igmun.homefinance.graph.infrastructure.rest;

import com.igmun.homefinance.graph.application.CategoryExpenseDataService;
import com.igmun.homefinance.graph.application.FetchPeriodsService;
import com.igmun.homefinance.graph.application.IncomeExpenseDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v0/graphs")
@RequiredArgsConstructor
public class GraphController {

  private final FetchPeriodsService fetchPeriodsService;
  private final IncomeExpenseDataService incomeExpenseDataService;
  private final CategoryExpenseDataService categoryExpenseDataService;

  @GetMapping("/periods")
  public PeriodsResponse getPeriods() {
    return new PeriodsResponse(fetchPeriodsService.fetchPeriods());
  }

  @GetMapping("/income-expense")
  public IncomeExpenseResponse getIncomeExpenseData(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate beginDate,
                                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
    return new IncomeExpenseResponse(incomeExpenseDataService.getData(beginDate, endDate));
  }

  @GetMapping("/category-expense")
  public CategoryExpenseResponse getCategoryExpenseData(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate beginDate,
                                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
    return new CategoryExpenseResponse(categoryExpenseDataService.getData(beginDate, endDate));
  }
}
