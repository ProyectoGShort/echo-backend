package xyz.proyectogshort.shared.infrastructure.event.inmemory;

import org.springframework.context.ApplicationEventPublisher;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.event.DomainEvent;
import xyz.proyectogshort.shared.domain.bus.event.EventBus;

import java.util.List;

@Service
public class InMemoryEventBus implements EventBus {
    private final ApplicationEventPublisher publisher;

    public InMemoryEventBus(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(final List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    private void publish(final DomainEvent event) {
        publisher.publishEvent(event);
    }
}
