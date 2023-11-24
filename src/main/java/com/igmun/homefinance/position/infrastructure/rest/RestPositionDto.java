package com.igmun.homefinance.position.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.time.LocalDate;

public record RestPositionDto(
  LocalDate date,
  String amount,
  String categoryGroup,
  String category,
  String type,
  String description
) {
}
