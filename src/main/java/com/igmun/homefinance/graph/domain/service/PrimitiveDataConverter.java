package com.igmun.homefinance.graph.domain.service;

import com.igmun.homefinance.graph.domain.PrimitiveData;
import com.igmun.homefinance.position.domain.Position;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Stream;

@RequiredArgsConstructor
public abstract class PrimitiveDataConverter<T extends RawDataExtractor> {

  private final T rawDataExtractor;

  public PrimitiveData getData(List<Position> positions) {
    return rawDataExtractor.extractData(positions).data().entrySet().stream()
      .reduce(
        PrimitiveData.empty(),
        primitiveDataAccumulator(),
        (primitiveData, primitiveData2) -> new PrimitiveData(
          Stream.concat(primitiveData.datasets().stream(), primitiveData2.datasets().stream()).toList(),
          primitiveData.labels()
        )
      );
  }

  private static BiFunction<PrimitiveData, ? super Map.Entry<String, Map<String, Double>>, PrimitiveData> primitiveDataAccumulator() {
    return (primitiveData, entry) -> {
      Set<String> dataLabels = new LinkedHashSet<>(entry.getValue().keySet());
      dataLabels.addAll(primitiveData.labels());
      String datasetLabel = entry.getKey();
      List<BigDecimal> data = entry.getValue().values().stream().map(value -> BigDecimal.valueOf(value).setScale(2, RoundingMode.DOWN)).toList();
      PrimitiveData.Dataset dataset = new PrimitiveData.Dataset(data, datasetLabel);
      List<PrimitiveData.Dataset> datasets = Stream.concat(primitiveData.datasets().stream(), Stream.of(dataset)).toList();
      return new PrimitiveData(datasets, dataLabels);
    };
  }
}
