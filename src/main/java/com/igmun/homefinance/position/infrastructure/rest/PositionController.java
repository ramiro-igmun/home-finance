package com.igmun.homefinance.position.infrastructure.rest;

import com.igmun.homefinance.position.application.AssignCategoryService;
import com.igmun.homefinance.position.application.BalanceService;
import com.igmun.homefinance.position.application.FetchPositionsService;
import com.igmun.homefinance.position.domain.Balance;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v0/positions")
@RequiredArgsConstructor
public class PositionController {

  private final RestPositionMapper restPositionMapper;
  private final FetchPositionsService retrievePositionsService;
  private final AssignCategoryService assignCategoryService;
  private final BalanceService balanceService;

  @GetMapping
  public ListPositionsResponse retrievePositions(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate beginDate,
                                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
    List<RestPositionDto> positions = retrievePositionsService.retrievePositions(beginDate, endDate).stream()
      .map(restPositionMapper::fromPosition).toList();
    return new ListPositionsResponse(positions);
  }

  @GetMapping("/balance")
  public Balance balance(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate beginDate,
                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
    return balanceService.getBalance(beginDate, endDate);
  }

  @PutMapping("category")
  @ResponseStatus(HttpStatus.CREATED)
  public void assignCategory(@RequestBody AssignCategoryRequest assignCategoryRequest) {
    assignCategoryService.assignCategory(assignCategoryRequest.description().substring(0, 10), assignCategoryRequest.tag());
  }

}
