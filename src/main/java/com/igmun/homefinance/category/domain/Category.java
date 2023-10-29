package com.igmun.homefinance.category.domain;

import jakarta.validation.constraints.NotNull;

public record Category(@NotNull String tag) {
  public Category(String tag) {
    this.tag = tag.toUpperCase();
  }
}
