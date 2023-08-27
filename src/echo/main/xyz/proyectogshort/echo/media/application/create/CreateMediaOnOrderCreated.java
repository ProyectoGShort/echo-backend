package xyz.proyectogshort.echo.media.application.create;

import xyz.proyectogshort.echo.order.domain.OrderCreatedEvent;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.event.EventListener;

@Service
public class CreateMediaOnOrderCreated {

    @EventListener
    public void on(OrderCreatedEvent orderCreatedEvent) {
        System.out.println("ORDEN RECIBIDA");
    }
}
