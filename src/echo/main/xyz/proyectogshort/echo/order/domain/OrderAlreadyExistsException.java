package xyz.proyectogshort.echo.order.domain;

import xyz.proyectogshort.echo.shared.domain.OrderId;

public final class OrderAlreadyExistsException extends RuntimeException {
    public OrderAlreadyExistsException(OrderId orderId){
        super(String.format("Order $%s already exists", orderId.value()));
    }
}
