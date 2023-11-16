package com.igmun.homefinance.graph.domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public record PrimitiveData(List<Dataset> datasets, Set<String> labels) {
  public record Dataset(List<BigDecimal> data, String label, String type){
    public Dataset(List<BigDecimal> data, String label) {
      this(data, label, "bar");
    }
  }

  public static PrimitiveData empty() {
    return new PrimitiveData(Collections.emptyList(), new LinkedHashSet<>());
  }

  public PrimitiveData addDataSet(Dataset dataset) {
    List<Dataset> newDatasets = Stream.concat(datasets.stream(), Stream.of(dataset)).toList();
    return new PrimitiveData(newDatasets, labels);
  }
}
