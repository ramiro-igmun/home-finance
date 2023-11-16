package com.igmun.homefinance.category.domain.event;

import com.igmun.homefinance.category.domain.Category;
import com.igmun.homefinance.shared.event.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public class CategoryDeletedEvent extends DomainEvent {
  private final EventData eventData;
  public CategoryDeletedEvent(EventData eventData, Instant creationTime) {
    super(UUID.randomUUID(), creationTime);
    this.eventData = eventData;
  }

  public static CategoryDeletedEvent from(Category category) {
    return new CategoryDeletedEvent(new EventData(category.getTag()), Instant.now());
  }

  @Override
  public EventData getData() {
    return eventData;
  }

  public record EventData(String tag){}
}
