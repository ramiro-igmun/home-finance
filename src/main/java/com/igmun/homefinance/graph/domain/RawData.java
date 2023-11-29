package com.igmun.homefinance.graph.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public record RawData(Map<String, Map<String, Double>> data) {

  public RawData {
    data = Collections.unmodifiableMap(data);
  }

  public RawData addDataset(String label, Map<String, Double> data) {
    HashMap<String, Map<String, Double>> currentData = new HashMap<>(this.data);
    currentData.put(label, data);
    return new RawData(currentData);
  }

}
