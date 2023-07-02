package com.igmun.homefinance.position.infrastructure.csv;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import lombok.Data;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RawPositionCsvDto {
  @JsonProperty("fecha")
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate date;
  @JsonProperty("concepto")
  private String description;
  @JsonProperty("fecha valor")
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate valueDate;
  @JsonProperty("importe")
  @JsonDeserialize(using = CustomBigDecimalDeserializer.class)
  private BigDecimal amount;
  @JsonProperty("saldo")
  private BigDecimal balance;

  public static class CustomBigDecimalDeserializer extends NumberDeserializers.BigDecimalDeserializer {

    @Override
    public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString()
        .replace("\"", "")
        .replace(",", "");
      return new BigDecimal(value);
    }
  }
}
