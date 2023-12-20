package com.igmun.homefinance.summary.domain;

import java.time.LocalDate;
import java.util.List;

public class ExpensesSummary {
  private LocalDate start;
  private LocalDate end;
  private List<GroupSummary> groups;
}
