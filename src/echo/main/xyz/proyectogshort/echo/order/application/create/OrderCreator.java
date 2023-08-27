package xyz.proyectogshort.echo.order.application.create;

import xyz.proyectogshort.echo.order.domain.OrderCreatedEvent;
import xyz.proyectogshort.echo.order.domain.OrderId;
import xyz.proyectogshort.echo.order.infrastructure.hibernate.OrderEntity;
import xyz.proyectogshort.echo.order.infrastructure.hibernate.OrderEntityRepository;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.event.EventBus;

import java.util.List;
import java.util.UUID;

@Service
public final class OrderCreator {
    private final OrderEntityRepository orderEntityRepository;
    private final EventBus bus;

    public OrderCreator(OrderEntityRepository orderEntityRepository, EventBus bus) {
        this.orderEntityRepository = orderEntityRepository;
        this.bus = bus;
    }

    public void create(OrderId orderId) {
        System.out.println("Order received: " + orderId);
        orderEntityRepository.save(new OrderEntity(UUID.randomUUID().toString()));
        bus.publish(List.of(new OrderCreatedEvent("test")));
    }
}
