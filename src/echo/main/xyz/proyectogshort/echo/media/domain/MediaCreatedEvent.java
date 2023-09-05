package xyz.proyectogshort.echo.media.domain;

import xyz.proyectogshort.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public final class MediaCreatedEvent extends DomainEvent {

    private final String orderId;

    public MediaCreatedEvent(String aggregateId, String orderId) {
        super(aggregateId);
        this.orderId = orderId;
    }

    public MediaCreatedEvent(String aggregateId, String eventId, String occurredOn, String orderId) {
        super(aggregateId, eventId, occurredOn);
        this.orderId = orderId;
    }

    @Override
    public String eventName() {
        return "media.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {{
            put("orderId", orderId);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new MediaCreatedEvent(aggregateId, eventId, occurredOn, (String) body.get("orderId"));
    }
}
