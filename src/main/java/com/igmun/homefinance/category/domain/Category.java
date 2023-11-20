package com.igmun.homefinance.category.domain;

import lombok.Getter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class Category {
  @Getter
  private final Long id;
  @Getter
  private final String tag;
  private final Set<Category> subCategories = new HashSet<>();

  public Category(String tag) {
    this(null, tag, Collections.emptySet());
  }

  public Category(Long id, String tag, Set<Category> subCategories) {
    this.id = id;
    this.tag = tag == null ? "" : tag.toUpperCase();
    this.subCategories.addAll(subCategories);
  }

  public void addSubCategory(String tag) {
    subCategories.add(new Category(tag));
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
