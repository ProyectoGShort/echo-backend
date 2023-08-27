package xyz.proyectogshort.shared.infrastructure.event.inmemory;

import org.aspectj.lang.ProceedingJoinPoint;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.event.DomainEvent;
import xyz.proyectogshort.shared.domain.bus.event.EventMiddleware;

import java.util.Map;

@Service
public class InMemoryHandlerMiddleware extends EventMiddleware {
    @Override
    public void process(DomainEvent event, Class<?> eventHandler, Map<String, Object> context) {

        ProceedingJoinPoint joinPoint = (ProceedingJoinPoint) context.get("joinPoint");

        if (joinPoint == null) {
            next(event, eventHandler, context);
            return;
        }

        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }

        next(event, eventHandler, context);
    }
}
