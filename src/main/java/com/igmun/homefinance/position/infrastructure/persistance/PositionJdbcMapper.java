package com.igmun.homefinance.position.infrastructure.persistance;

import com.igmun.homefinance.category.domain.Category;
import com.igmun.homefinance.position.domain.Position;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PositionJdbcMapper {
  public PositionJdbc fromPosition(Position position) {
    return new PositionJdbc(position.getId(), position.getDate(), position.getAmount(),
      position.getCategory() == null ? null : position.getCategory().getTag(),
      position.getCategory() == null ? null : position.getParentCategory().getTag(),
      position.getType(), position.getDescription());
  }

  public Position toPosition(PositionJdbc positionJdbc) {
    return new Position(positionJdbc.getId(), positionJdbc.getDate(), positionJdbc.getAmount(),
      new Category(positionJdbc.getCategory()), new Category(positionJdbc.getParentCategory()), positionJdbc.getType(),
      positionJdbc.getDescription());
  }
}
