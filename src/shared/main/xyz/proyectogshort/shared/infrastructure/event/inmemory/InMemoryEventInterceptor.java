package xyz.proyectogshort.shared.infrastructure.event.inmemory;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import xyz.proyectogshort.shared.domain.bus.event.DomainEvent;
import xyz.proyectogshort.shared.domain.bus.event.EventMiddlewareChain;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public final class InMemoryEventInterceptor {

    private final EventMiddlewareChain eventMiddlewareChain;

    public InMemoryEventInterceptor(EventMiddlewareChain eventMiddlewareChain) {
        this.eventMiddlewareChain = eventMiddlewareChain;
    }

    @Around("@annotation(xyz.proyectogshort.shared.domain.bus.event.EventListener)")
    public Object interceptEventListener(ProceedingJoinPoint joinPoint) {

        DomainEvent event = (DomainEvent) joinPoint.getArgs()[0];
        Class<?> eventHandler = joinPoint.getTarget().getClass();

        Map<String, Object> context = new HashMap<>();
        context.put("joinPoint", joinPoint);

        this.eventMiddlewareChain.process(event, eventHandler, context);
        return null;
    }

}
