package xyz.proyectogshort.shared.domain.bus.event;

public abstract class EventMiddleware {
    private EventMiddleware next;

    public EventMiddleware() {}

    public void setNext(EventMiddleware next) {
        this.next = next;
    }

    public abstract void process(DomainEvent event);

    protected void next(DomainEvent event) {
        if (next == null) {
            return;
        }

        next.process(event);
    }
}
