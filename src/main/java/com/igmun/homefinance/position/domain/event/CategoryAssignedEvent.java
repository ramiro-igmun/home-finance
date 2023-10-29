package com.igmun.homefinance.position.domain.event;

import com.igmun.homefinance.category.domain.Category;
import com.igmun.homefinance.shared.event.DomainEvent;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class CategoryAssignedEvent extends DomainEvent {
  private final Data data;
  public CategoryAssignedEvent(Data data, Instant creationTime) {
    super(UUID.randomUUID(), creationTime);
    this.data = data;
  }

  public static CategoryAssignedEvent from(Category category) {
    return new CategoryAssignedEvent(new Data(category.tag()), Instant.now());
  }
  public record Data(String tag){}
}
