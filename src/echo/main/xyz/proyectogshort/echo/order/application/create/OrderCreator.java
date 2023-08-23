package xyz.proyectogshort.echo.order.application.create;

import xyz.proyectogshort.echo.order.domain.OrderId;
import xyz.proyectogshort.shared.domain.Service;

@Service
public final class OrderCreator {
    public void create(OrderId orderId) {
        System.out.println("Order received: " + orderId);
    }
}
