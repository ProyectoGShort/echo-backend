package xyz.proyectogshort.shared.domain.bus.event;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@org.springframework.context.event.EventListener
public @interface EventListener {}
