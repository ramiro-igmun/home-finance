package com.igmun.homefinance.position.infrastructure.persistance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PositionJdbcRepositoryTest {
  @Autowired
  private PositionJdbcRepository positionJdbcRepository;

  @Test
  void testGetOldest() {
    PositionJdbc oldestPosition = positionJdbcRepository.getOldestPosition();
    assertThat(oldestPosition).isNotNull();
  }
}
