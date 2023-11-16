package com.igmun.homefinance.graph.domain.service;

import com.igmun.homefinance.graph.domain.ObjectData;
import com.igmun.homefinance.position.domain.Position;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

@RequiredArgsConstructor
public abstract class ObjectDataConverter<T extends RawDataExtractor> {

  private final T rawDataExtractor;

  public ObjectData getData(List<Position> positions) {
    return rawDataExtractor.extractData(positions).data().entrySet().stream()
      .reduce(
        ObjectData.empty(),
        objectDataAccumulator(),
        (primitiveData, primitiveData2) -> new ObjectData(
          Stream.concat(primitiveData.datasets().stream(), primitiveData2.datasets().stream()).toList()
        )
      );
  }

  private static BiFunction<ObjectData, ? super Map.Entry<String, Map<String, Double>>, ObjectData> objectDataAccumulator() {
    return (objectData, entry) -> {
      List<ObjectData.Data> data = entry.getValue().entrySet().stream().map(dataEntry -> new ObjectData.Data(dataEntry.getKey(),
        BigDecimal.valueOf(dataEntry.getValue()))).toList();
      ObjectData.Dataset dataset = new ObjectData.Dataset(data, entry.getKey());
      return objectData.addDataSet(dataset);
    };
  }
}
