package com.igmun.homefinance.shared.event;

import java.util.Collection;

public interface DomainEventPublisher {
  void publish(DomainEvent event);
  void publish(Collection<DomainEvent> events);
}
