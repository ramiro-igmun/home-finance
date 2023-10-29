package com.igmun.homefinance.position.infrastructure.rest;

import com.igmun.homefinance.position.application.AssignCategoryService;
import com.igmun.homefinance.position.application.RetrievePositionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v0/positions")
@RequiredArgsConstructor
public class PositionController {

  private final RestPositionMapper restPositionMapper;
  private final RetrievePositionsService retrievePositionsService;
  private final AssignCategoryService assignCategoryService;

  @GetMapping
  public ListPositionsResponse retrievePositions() {
    List<RestPositionDto> positions = retrievePositionsService.retrievePositions().stream()
      .map(restPositionMapper::fromPosition).toList();
    return new ListPositionsResponse(positions);
  }

  @PutMapping("category")
  @ResponseStatus(HttpStatus.CREATED)
  public void assignCategory(@RequestBody AssignCategoryRequest assignCategoryRequest) {
    assignCategoryService.assignCategory(assignCategoryRequest.description(), assignCategoryRequest.tag());
  }

}
