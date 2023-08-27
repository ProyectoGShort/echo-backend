package xyz.proyectogshort.shared.infrastructure.event.middleware;

import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.event.DomainEvent;
import xyz.proyectogshort.shared.domain.bus.event.EventMiddleware;

@Service
public class EventTransactionMiddleware extends EventMiddleware {
    @Override
    public void process(DomainEvent event, Class<?> eventHandler) {
        System.out.println(event);
        System.out.println(eventHandler.getName());
    }
}
