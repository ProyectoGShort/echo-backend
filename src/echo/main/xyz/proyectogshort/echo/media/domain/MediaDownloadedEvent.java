package xyz.proyectogshort.echo.media.domain;

import xyz.proyectogshort.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public final class MediaDownloadedEvent extends DomainEvent {
    public MediaDownloadedEvent(String aggregateId) {
        super(aggregateId);
    }

    public MediaDownloadedEvent(String aggregateId, String eventId, String occurredOn) {
        super(aggregateId, eventId, occurredOn);
    }

    @Override
    public String eventName() {
        return "media.downloaded";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>();
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new MediaDownloadedEvent(aggregateId, eventId, occurredOn);
    }
}
