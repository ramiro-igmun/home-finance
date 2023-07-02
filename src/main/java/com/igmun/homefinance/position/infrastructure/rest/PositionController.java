package com.igmun.homefinance.position.infrastructure.rest;

import com.igmun.homefinance.position.application.RetrievePositionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v0/positions")
@RequiredArgsConstructor
public class PositionController {

  private final RestPositionMapper restPositionMapper;
  private final RetrievePositionsService retrievePositionsService;

  @GetMapping
  public ListPositionsResponse retrievePositions() {
    List<RestPositionDto> positions = retrievePositionsService.retrievePositions().stream()
      .map(restPositionMapper::fromPosition).toList();
    return new ListPositionsResponse(positions);
  }

}
