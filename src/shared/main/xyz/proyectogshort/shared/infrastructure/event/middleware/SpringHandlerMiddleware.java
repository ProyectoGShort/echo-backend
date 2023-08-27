package xyz.proyectogshort.shared.infrastructure.event.middleware;

import org.springframework.context.ApplicationEventPublisher;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.event.DomainEvent;
import xyz.proyectogshort.shared.domain.bus.event.EventMiddleware;

@Service
public class SpringHandlerMiddleware extends EventMiddleware {

    private final ApplicationEventPublisher publisher;

    public SpringHandlerMiddleware(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void process(DomainEvent event) {
        this.publisher.publishEvent(event);
    }
}
