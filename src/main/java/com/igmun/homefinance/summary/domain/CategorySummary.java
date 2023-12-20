package com.igmun.homefinance.summary.domain;

import com.igmun.homefinance.category.domain.Category;

import java.math.BigDecimal;

public class CategorySummary {
  private Category category;
  private BigDecimal total;
  private BigDecimal monthlyAverage;
}
