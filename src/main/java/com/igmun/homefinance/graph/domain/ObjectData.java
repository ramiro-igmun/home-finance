package com.igmun.homefinance.graph.domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public record ObjectData(List<Dataset> datasets) {

  public record Dataset(List<Data> data, String label, String type){
    public Dataset(List<Data> data, String label) {
      this(data, label, "bar");
    }
  }

  public record Data(String x, BigDecimal y) {}

  public static ObjectData empty() {
    return new ObjectData(Collections.emptyList());
  }

  public ObjectData addDataSet(Dataset dataset) {
    List<Dataset> newDatasets = Stream.concat(datasets.stream(), Stream.of(dataset)).toList();
    return new ObjectData(newDatasets);
  }
}
