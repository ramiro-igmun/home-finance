package com.igmun.homefinance.position.domain;

import com.igmun.homefinance.category.domain.Category;
import com.igmun.homefinance.position.domain.event.CategoryAssignedEvent;
import com.igmun.homefinance.shared.event.DomainEvent;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Position {
  private Long id;
  private LocalDate date;
  private BigDecimal amount;
  private Category category = new Category("");
  private Type type;
  private String description = "";
  @Getter(AccessLevel.NONE)
  private List<DomainEvent> events = new ArrayList<>();

  public static Position createPosition(LocalDate date, BigDecimal amount, String description) {
    Position position = new Position();
    position.date = date;
    position.amount = amount;
    position.description = description;
    position.type = Type.fromAmount(amount);
    return position;
  }

  public void setCategory(Category category) {
    this.category = category;
    events.add(CategoryAssignedEvent.from(category));
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
