package com.igmun.homefinance.shared.infraestructure;

import com.igmun.homefinance.shared.event.DomainEvent;
import com.igmun.homefinance.shared.event.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class ApplicationEventBus implements DomainEventPublisher {
  private final ApplicationEventPublisher eventPublisher;

  @Override
  public void publish(DomainEvent event) {
    eventPublisher.publishEvent(event);
  }

  @Override
  public void publish(Collection<DomainEvent> events) {
    events.forEach(this::publish);
  }
}
