package xyz.proyectogshort.echo.order.domain;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(OrderId orderId) {
        super(String.format("Order %s not found", orderId.value()));
    }
}
