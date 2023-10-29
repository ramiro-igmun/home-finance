package com.igmun.homefinance;

import com.igmun.homefinance.position.domain.PositionRepository;
import com.igmun.homefinance.position.domain.Position;
import com.igmun.homefinance.position.infrastructure.csv.RawPositionCsvDto;
import com.igmun.homefinance.position.infrastructure.csv.RawPositionCsvRepository;
import com.igmun.homefinance.position.infrastructure.csv.RawPositionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class HomeFinanceApplication implements CommandLineRunner {

  private final RawPositionCsvRepository rawPositionCsvRepository;
  private final RawPositionMapper rawPositionMapper;
  private final PositionRepository positionRepository;

  public static void main(String[] args) {
    SpringApplication.run(HomeFinanceApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    List<RawPositionCsvDto> rawPositions = rawPositionCsvRepository.getList();
    List<Position> positions = rawPositions.stream().map(rawPositionMapper::fromRawPosition).toList();
    //TODO add new positions after the last position already persisted and apply tags automatically
    if (positionRepository.getAll().isEmpty()) {
      positionRepository.saveAll(positions);
    }
  }
}
