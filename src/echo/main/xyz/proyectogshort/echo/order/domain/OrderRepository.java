package xyz.proyectogshort.echo.order.domain;

import xyz.proyectogshort.echo.shared.domain.OrderId;

import java.util.Optional;

public interface OrderRepository {
    void save(Order order);
    Optional<Order> findById(OrderId orderId);
}
