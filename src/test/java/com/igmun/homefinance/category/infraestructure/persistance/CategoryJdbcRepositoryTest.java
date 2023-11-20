package com.igmun.homefinance.category.infraestructure.persistance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CategoryJdbcRepositoryTest {

  @Autowired
  private CategoryJdbcRepository repository;

  @Test
  void test_get_parent() {
    CategoryJdbc category = repository.findParentCategory("SUBCATEGORY").orElseThrow();
    assertThat(category).isNotNull();
  }
}
