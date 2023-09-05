package xyz.proyectogshort.echo.order.domain;

import xyz.proyectogshort.echo.shared.domain.OrderId;

public final class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(OrderId orderId) {
        super(String.format("Order %s not found", orderId.value()));
    }
}
