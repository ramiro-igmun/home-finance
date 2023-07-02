package com.igmun.homefinance.category.domain;

import jakarta.validation.constraints.NotNull;

public record Category(Long id, @NotNull String tag) {
  public Category(String tag) {
    this(null, tag);
  }
}
