package com.igmun.homefinance.position.infrastructure.rest;

import com.igmun.homefinance.position.domain.Position;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;

@Component
public class RestPositionMapper {

  public RestPositionDto fromPosition(Position position) {
    return new RestPositionDto(position.getDate(),
      position.getAmount().setScale(2, RoundingMode.DOWN).toPlainString(),
      position.getCategory().tag(),
      position.getType().toString(),
      position.getDescription());
  }
}
