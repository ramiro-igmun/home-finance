package com.igmun.homefinance.position.infrastructure.persistance;

import com.igmun.homefinance.position.domain.Position;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Table("POSITION")
@RequiredArgsConstructor
public class PositionJdbc {
  @Id
  private final Long id;
  private final LocalDate date;
  private final BigDecimal amount;
  private final String category;
  private final Position.Type type;
  private final String description;
}
