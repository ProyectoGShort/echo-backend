package xyz.proyectogshort.echo.order.application.create;

import xyz.proyectogshort.echo.order.domain.*;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.event.EventBus;

@Service
public final class OrderCreator {
    private final OrderRepository repository;
    private final EventBus bus;

    public OrderCreator(OrderRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }

    public void create(OrderId orderId, OrderSourceUrl orderSourceUrl) {
        Order order = Order.create(orderId, "Test", OrderSource.YOUTUBE, orderSourceUrl, 0, "");
        repository.save(order);
        bus.publish(order.pullDomainEvents());
    }
}
