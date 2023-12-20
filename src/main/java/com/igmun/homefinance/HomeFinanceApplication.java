package com.igmun.homefinance;

import com.igmun.homefinance.category.domain.Category;
import com.igmun.homefinance.category.domain.CategoryRepository;
import com.igmun.homefinance.position.domain.PositionRepository;
import com.igmun.homefinance.position.domain.Position;
import com.igmun.homefinance.position.infrastructure.csv.RawPositionCsvDto;
import com.igmun.homefinance.position.infrastructure.csv.RawPositionCsvRepository;
import com.igmun.homefinance.position.infrastructure.csv.RawPositionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
@RequiredArgsConstructor
public class HomeFinanceApplication implements CommandLineRunner {

  private final RawPositionCsvRepository rawPositionCsvRepository;
  private final RawPositionMapper rawPositionMapper;
  private final PositionRepository positionRepository;
  private final CategoryRepository categoryRepository;

  public static void main(String[] args) {
    SpringApplication.run(HomeFinanceApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    List<RawPositionCsvDto> rawPositions = rawPositionCsvRepository.getList();
    List<Position> positions = rawPositions.stream().map(rawPositionMapper::fromRawPosition).toList();
    List<Position> persistedPositions = positionRepository.getAll();
    if (persistedPositions.isEmpty()) {
      positionRepository.saveAll(positions);
    } else {
      Position newestPosition = positionRepository.getNewestPosition();
      List<Position> afterNewestPosition = positions.stream()
        .filter(position -> position.getDate().isAfter(newestPosition.getDate()))
        .toList();
      positionRepository.saveAll(Stream.concat(persistedPositions.stream(), afterNewestPosition.stream()).toList());
    }
    if (categoryRepository.getAll().isEmpty()) {
      categoryRepository.save(Category.fromTag("Salary Ramiro"));
      categoryRepository.save(Category.fromTag("Salary Leticia"));
    }
  }
}
