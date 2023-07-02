package com.igmun.homefinance.category.infraestructure.persistance;

import org.springframework.data.annotation.Id;

public class CategoryJdbc {
  @Id
  public Long id;
  public String tag;
}
