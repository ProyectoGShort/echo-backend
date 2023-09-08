package xyz.proyectogshort.echo.media.application.create;

import xyz.proyectogshort.echo.shared.domain.OrderCreatedEvent;
import xyz.proyectogshort.echo.shared.domain.OrderId;
import xyz.proyectogshort.echo.shared.domain.Source;
import xyz.proyectogshort.echo.shared.domain.OrderSourceUrl;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.event.EventListener;

@Service
public class CreateMediaOnOrderCreated {

    private final MultiMediaCreator mediaCreator;

    public CreateMediaOnOrderCreated(MultiMediaCreator mediaCreator) {
        this.mediaCreator = mediaCreator;
    }

    @EventListener
    public void on(OrderCreatedEvent event) {
        mediaCreator.createMultiple(
                new OrderId(event.aggregateId()),
                Source.valueOf(event.getOrderSource()),
                new OrderSourceUrl(event.getOrderSourceUrl()),
                event.getOrderMediaCount()
        );
    }
}
