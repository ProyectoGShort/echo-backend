package xyz.proyectogshort.echo.media.domain;

import xyz.proyectogshort.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public class MediaDownloadStartedEvent extends DomainEvent {
    private final String orderId;

    public MediaDownloadStartedEvent(String aggregateId, String orderId) {
        super(aggregateId);
        this.orderId = orderId;
    }

    public MediaDownloadStartedEvent(String aggregateId, String eventId, String occurredOn, String orderId) {
        super(aggregateId, eventId, occurredOn);
        this.orderId = orderId;
    }

    @Override
    public String eventName() {
        return "media.download_started";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {{
            put("orderId", orderId);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new MediaDownloadStartedEvent(
                aggregateId,
                eventId,
                occurredOn,
                (String) body.get("orderId")
        );
    }
}
