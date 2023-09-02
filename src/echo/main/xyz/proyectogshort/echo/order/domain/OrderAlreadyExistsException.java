package xyz.proyectogshort.echo.order.domain;

public class OrderAlreadyExistsException extends RuntimeException {
    public OrderAlreadyExistsException(OrderId orderId){
        super(String.format("Order $%s already exists", orderId.value()));
    }
}
