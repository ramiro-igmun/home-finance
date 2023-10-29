package com.igmun.homefinance.category.infraestructure.persistance;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Table("CATEGORY")
@RequiredArgsConstructor
public class CategoryJdbc {
  @Id
  private final Long id;
  private final String tag;
}
