package xyz.proyectogshort.echo.order.domain;

import xyz.proyectogshort.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public class OrderCreatedEvent extends DomainEvent {

    public OrderCreatedEvent(
        String aggregateId
    ) {
        super(aggregateId);
    }

    @Override
    public String eventName() {
        return "order.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return null;
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return null;
    }
}
