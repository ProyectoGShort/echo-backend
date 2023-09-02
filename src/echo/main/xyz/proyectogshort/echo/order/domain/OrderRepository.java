package xyz.proyectogshort.echo.order.domain;

import java.util.Optional;

public interface OrderRepository {
    void save(Order order);
    Optional<Order> findById(OrderId orderId);
}
