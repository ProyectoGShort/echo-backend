package xyz.proyectogshort.shared.infrastructure.event;

import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.event.DomainEvent;
import xyz.proyectogshort.shared.domain.bus.event.EventBus;
import xyz.proyectogshort.shared.domain.bus.event.EventMiddlewareChain;

import java.util.List;

@Service
public class InMemoryEventBus implements EventBus {
    private final EventMiddlewareChain eventMiddlewareChain;

    public InMemoryEventBus(EventMiddlewareChain eventMiddlewareChain) {
        this.eventMiddlewareChain = eventMiddlewareChain;
    }

    @Override
    public void publish(final List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    private void publish(final DomainEvent event) {
        eventMiddlewareChain.process(event);
    }
}
