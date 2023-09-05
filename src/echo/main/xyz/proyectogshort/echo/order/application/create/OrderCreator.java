package xyz.proyectogshort.echo.order.application.create;

import xyz.proyectogshort.echo.order.domain.*;
import xyz.proyectogshort.echo.shared.domain.OrderId;
import xyz.proyectogshort.echo.shared.domain.OrderSourceUrl;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.event.EventBus;

@Service
public final class OrderCreator {
    private final OrderInfoFetcherGateway orderInfoFetcherGateway;
    private final OrderRepository repository;
    private final EventBus bus;

    public OrderCreator(OrderInfoFetcherGateway orderInfoFetcherGateway, OrderRepository repository, EventBus bus) {
        this.orderInfoFetcherGateway = orderInfoFetcherGateway;
        this.repository = repository;
        this.bus = bus;
    }

    public void create(OrderId orderId, OrderSourceUrl orderSourceUrl) {
        OrderInfo orderInfo = orderInfoFetcherGateway.fetch(orderSourceUrl);
        Order order = Order.create(orderId, orderSourceUrl, orderInfo);

        repository
                .findById(orderId)
                .ifPresent((existingOrder) -> {
                    throw new OrderAlreadyExistsException(orderId);
                });

        repository.save(order);
        bus.publish(order.pullDomainEvents());
    }
}
