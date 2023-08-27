package xyz.proyectogshort.shared.domain.bus.event;

import java.util.Map;

public abstract class EventMiddleware {
    private EventMiddleware next;

    public EventMiddleware() {}

    public void setNext(EventMiddleware next) {
        this.next = next;
    }

    public abstract void process(DomainEvent event, Class<?> eventHandler, Map<String, Object> context);

    protected void next(DomainEvent event, Class<?> eventHandler, Map<String, Object> context) {
        if (next == null) {
            return;
        }

        next.process(event, eventHandler, context);
    }
}
