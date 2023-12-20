package com.igmun.homefinance.category.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public final class Category {
  @Getter
  private final Long id;
  @Getter
  private final String tag;
  @Getter
  @Setter
  private String color;
  private final Set<Category> subCategories = new HashSet<>();

  public static Category fromTag(String tag) {
    return new Category(tag, "red");
  }

  public Category(String tag, String color) {
    this(null, tag, color, Collections.emptySet());
  }

  public Category(Long id, String tag, String color, Set<Category> subCategories) {
    this.id = id;
    this.tag = tag == null ? "" : tag.toUpperCase();
    this.color = color == null ? "#eb4034" : color;
    this.subCategories.addAll(subCategories);
  }

  public void addSubCategory(String tag) {
    subCategories.add(new Category(null, tag, this.color, Collections.emptySet()));
  }

  public Set<Category> getSubCategories() {
    return Collections.unmodifiableSet(subCategories);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Category category = (Category) o;

      return tag.equals(category.tag);
  }

  @Override
  public int hashCode() {
    return tag.hashCode();
  }
}
