package com.igmun.homefinance.summary.domain;

import com.igmun.homefinance.category.domain.Category;

import java.math.BigDecimal;
import java.util.List;

public class GroupSummary {
  private Category group;
  private BigDecimal total;
  private BigDecimal monthlyAverage;
  private List<CategorySummary> categories;
}
