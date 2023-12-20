package com.igmun.homefinance.position.domain;

import com.igmun.homefinance.category.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Position {
  private Long id;
  private LocalDate date;
  private BigDecimal amount = BigDecimal.ZERO;
  @Setter
  private Category category = Category.fromTag("");
  @Setter
  private Category parentCategory = Category.fromTag("");
  private Type type;
  private String description = "";

  public static Position createPosition(LocalDate date, BigDecimal amount, String description) {
    Position position = new Position();
    position.date = date;
    position.amount = amount;
    position.description = description;
    position.type = Type.fromAmount(amount);
    return position;
  }

  public void unAssignCategory() {
    this.category = null;
    this.parentCategory = null;
  }

  public enum Type {
    INCOME, EXPENSE;

    public static Type fromAmount(BigDecimal amount) {
      if (amount.signum() == -1) {
        return EXPENSE;
      }
      return INCOME;
    }
  }
}
