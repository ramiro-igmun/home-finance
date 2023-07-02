package com.igmun.homefinance.position.infrastructure.csv;

import com.igmun.homefinance.position.domain.Position;
import com.igmun.homefinance.position.infrastructure.csv.RawPositionCsvDto;
import org.springframework.stereotype.Component;

@Component
public class RawPositionMapper {
  public Position fromRawPosition(RawPositionCsvDto rawPosition) {
    return Position.createPosition(rawPosition.getValueDate(), rawPosition.getAmount(),
      rawPosition.getDescription());
  }
}
