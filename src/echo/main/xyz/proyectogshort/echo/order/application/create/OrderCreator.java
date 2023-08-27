package xyz.proyectogshort.echo.order.application.create;

import xyz.proyectogshort.echo.order.domain.OrderId;
import xyz.proyectogshort.echo.order.infrastructure.hibernate.OrderEntity;
import xyz.proyectogshort.echo.order.infrastructure.hibernate.OrderEntityRepository;
import xyz.proyectogshort.shared.domain.Service;

import java.util.UUID;

@Service
public final class OrderCreator {
    private final OrderEntityRepository orderEntityRepository;

    public OrderCreator(OrderEntityRepository orderEntityRepository) {
        this.orderEntityRepository = orderEntityRepository;
    }

    public void create(OrderId orderId) {
        System.out.println("Order received: " + orderId);
        orderEntityRepository.save(new OrderEntity(UUID.randomUUID().toString()));
        throw new RuntimeException("test transaction");
    }
}
