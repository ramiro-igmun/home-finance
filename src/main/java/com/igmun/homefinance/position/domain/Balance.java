package com.igmun.homefinance.position.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Balance(BigDecimal income, BigDecimal expense, BigDecimal balance) {

  public Balance {
    if (balance.compareTo(income.add(expense)) != 0) {
      throw new IllegalArgumentException("Balance must be equal to income plus expense");
    }
    income = income.setScale(2, RoundingMode.DOWN);
    expense = expense.setScale(2, RoundingMode.DOWN);
  }

  public Balance() {
    this(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
  }

  public Balance(BigDecimal income, BigDecimal expense) {
    this(income, expense, income.add(expense).setScale(2, RoundingMode.DOWN));
  }

}
