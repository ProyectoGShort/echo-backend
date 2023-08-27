package xyz.proyectogshort.shared.domain.bus.event;

import xyz.proyectogshort.shared.domain.Service;

import java.util.List;
import java.util.Map;

@Service
public class EventMiddlewareChain {
    private EventMiddleware head;

    public EventMiddlewareChain(List<EventMiddleware> chain) {

        if (chain.isEmpty()) {
            return;
        }

        EventMiddleware previous = chain.get(0);
        head = chain.remove(0);

        for (EventMiddleware nextInChain: chain) {
            previous.setNext(nextInChain);
            previous = nextInChain;
        }
    }

    public void process(DomainEvent event, Class<?> eventHandler, Map<String, Object> context) {
        if (head == null) {
            return;
        }

        head.process(event, eventHandler, context);
    }
}
