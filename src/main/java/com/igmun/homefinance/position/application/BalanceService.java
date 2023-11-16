package com.igmun.homefinance.position.application;

import com.igmun.homefinance.position.domain.Balance;
import com.igmun.homefinance.position.domain.Position;
import com.igmun.homefinance.position.domain.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.function.BiFunction;

@Service
@RequiredArgsConstructor
public class BalanceService {
  private final PositionRepository positionRepository;

  public Balance getBalance(LocalDate beginDate, LocalDate endDate) {
    return positionRepository.findByDateBetween(beginDate, endDate).stream().reduce(
      new Balance(),
      accumulator(),
      (balance, balance2) -> new Balance(balance.income().add(balance2.income()), balance.expense().add(balance2.expense()))
    );
  }

  public BiFunction<Balance, Position, Balance> accumulator() {
    return (balance, position) -> {
      if (position.getType() == Position.Type.INCOME) {
        return new Balance(
          balance.income().add(position.getAmount()),
          balance.expense());
      } else {
        return new Balance(
          balance.income(),
          balance.expense().add(position.getAmount()));
      }
    };
  }
}
