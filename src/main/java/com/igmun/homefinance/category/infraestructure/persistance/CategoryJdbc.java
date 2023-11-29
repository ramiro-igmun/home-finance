package com.igmun.homefinance.category.infraestructure.persistance;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Getter
@Table("CATEGORY")
@RequiredArgsConstructor
public class CategoryJdbc {
  @Id
  private final Long id;
  private final String tag;
  private final String color;
  @MappedCollection(idColumn = "PARENT_CATEGORY_ID")
  private Set<CategoryJdbc> subCategories = new HashSet<>();

  @PersistenceCreator
  public CategoryJdbc(Long id, String tag, String color, Set<CategoryJdbc> subCategories) {
    this.id = id;
    this.tag = tag;
    this.color = color;
    this.subCategories = subCategories;
  }
}
