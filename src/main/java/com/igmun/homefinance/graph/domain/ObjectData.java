package com.igmun.homefinance.graph.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ObjectData(List<Dataset> datasets) {

  public record Dataset(List<Data> data, String label, String type, String backgroundColor){
    public Dataset(List<Data> data, String label) {
      this(data, label, "bar", null);
    }

    public Dataset withColor(String color) {
      return new Dataset(this.data, this.label, this.type, color);
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
