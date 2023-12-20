package com.igmun.homefinance.summary.infraestructure.rest;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class ExpensesSummaryResponse {
  private LocalDate start;
  private LocalDate end;
  private List<GroupSummaryResponse> groups;

  @Data
  public static class GroupSummaryResponse {
    private String group;
    private String color;
    private BigDecimal total;
    private BigDecimal monthlyAverage;
    private List<CategorySummaryResponse> categories;
  }

  @Data
  public static class CategorySummaryResponse {
    private String category;
    private BigDecimal total;
    private BigDecimal monthlyAverage;
  }
}
