package xyz.proyectogshort.echo.order.application.find;

import xyz.proyectogshort.echo.order.domain.Order;
import xyz.proyectogshort.echo.shared.domain.OrderId;
import xyz.proyectogshort.echo.order.domain.OrderNotFoundException;
import xyz.proyectogshort.echo.order.domain.OrderRepository;
import xyz.proyectogshort.shared.domain.Service;

@Service
public final class OrderFinder {
    private final OrderRepository repository;

    public OrderFinder(OrderRepository repository) {
        this.repository = repository;
    }

    public OrderResponse find(OrderId orderId) {
        Order order = repository
                .findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        return OrderResponse.fromAggregate(order);
    }
}
