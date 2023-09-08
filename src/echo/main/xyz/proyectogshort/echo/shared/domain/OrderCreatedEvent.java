package xyz.proyectogshort.echo.shared.domain;

import xyz.proyectogshort.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public final class OrderCreatedEvent extends DomainEvent {

    private final String orderSource;
    private final String orderSourceUrl;
    private final long orderMediaCount;

    public OrderCreatedEvent(
        String aggregateId,
        String orderSource,
        String orderSourceUrl,
        long orderMediaCount
    ) {
        super(aggregateId);

        this.orderSource = orderSource;
        this.orderSourceUrl = orderSourceUrl;
        this.orderMediaCount = orderMediaCount;
    }

    public OrderCreatedEvent(
            String aggregateId,
            String eventId,
            String occurredOn,
            String orderSource,
            String orderSourceUrl,
            long orderMediaCount

    ) {
        super(aggregateId, eventId, occurredOn);

        this.orderSource = orderSource;
        this.orderSourceUrl = orderSourceUrl;
        this.orderMediaCount = orderMediaCount;

    }

    public String getOrderSource() {
        return orderSource;
    }

    public String getOrderSourceUrl() {
        return orderSourceUrl;
    }

    public long getOrderMediaCount() {
        return orderMediaCount;
    }

    @Override
    public String eventName() {
        return "order.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
       return new HashMap<>() {{
           put("orderSource", orderSource);
           put("orderSourceUrl", orderSourceUrl);
           put("orderMediaCount", orderMediaCount);
       }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new OrderCreatedEvent(
                aggregateId,
                eventId,
                occurredOn,
                (String) body.get("source"),
                (String) body.get("orderSourceUrl"),
                (long) body.get("orderMediaCount")
        );
    }
}
