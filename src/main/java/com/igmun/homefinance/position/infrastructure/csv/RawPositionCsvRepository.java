package com.igmun.homefinance.position.infrastructure.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class RawPositionCsvRepository {
  private static final String POSITIONS_CSV = "positions.csv";
  private final ObjectMapper csvMapper = new CsvMapper()
    .registerModule(new JavaTimeModule());
  private final CsvSchema schema = CsvSchema.emptySchema().withHeader();

  public List<RawPositionCsvDto> getList() throws IOException {
    InputStream inputStream = new ClassPathResource(POSITIONS_CSV).getInputStream();
    MappingIterator<RawPositionCsvDto> iterator = csvMapper
      .readerFor(RawPositionCsvDto.class)
      .with(schema)
      .readValues(inputStream);
    return iterator.readAll();
  }
}
