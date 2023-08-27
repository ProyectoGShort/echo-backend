package xyz.proyectogshort.shared.domain.bus.event;

public abstract class EventMiddleware {
    private EventMiddleware next;

    public EventMiddleware() {}

    public void setNext(EventMiddleware next) {
        this.next = next;
    }

    public abstract void process(DomainEvent event, Class<?> eventHandler);

    protected void next(DomainEvent event, Class<?> eventHandler) {
        if (next == null) {
            return;
        }

        next.process(event, eventHandler);
    }
}
