package com.igmun.homefinance.shared.event;

import lombok.AllArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
public abstract class DomainEvent {
  private UUID id;
  private Instant creationTime;
  protected abstract Object getData();
}
