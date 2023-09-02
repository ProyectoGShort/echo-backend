package xyz.proyectogshort.echo.order.domain;

import xyz.proyectogshort.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public class OrderCreatedEvent extends DomainEvent {

    private final String orderSource;
    private final String orderSourceUrl;

    public OrderCreatedEvent(
        String aggregateId,
        String orderSource,
        String orderSourceUrl
    ) {
        super(aggregateId);

        this.orderSource = orderSource;
        this.orderSourceUrl = orderSourceUrl;
    }

    public OrderCreatedEvent(
            String aggregateId,
            String eventId,
            String occurredOn,
            String orderSource,
            String orderSourceUrl
    ) {
        super(aggregateId, eventId, occurredOn);

        this.orderSource = orderSource;
        this.orderSourceUrl = orderSourceUrl;
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
       }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new OrderCreatedEvent(
                aggregateId,
                eventId,
                occurredOn,
                (String) body.get("orderSource"),
                (String) body.get("orderSourceUrl")
        );
    }
}
