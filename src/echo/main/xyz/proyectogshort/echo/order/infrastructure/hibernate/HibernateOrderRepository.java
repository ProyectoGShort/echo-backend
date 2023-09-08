package xyz.proyectogshort.echo.order.infrastructure.hibernate;

import xyz.proyectogshort.echo.order.domain.Order;
import xyz.proyectogshort.echo.order.domain.OrderRepository;
import xyz.proyectogshort.echo.shared.domain.OrderId;
import xyz.proyectogshort.shared.domain.Service;

import java.util.Optional;

@Service
public final class HibernateOrderRepository implements OrderRepository {

    private final OrderEntityRepository orderEntityRepository;

    public HibernateOrderRepository(OrderEntityRepository orderEntityRepository) {
        this.orderEntityRepository = orderEntityRepository;
    }

    @Override
    public void save(Order order) {
        OrderEntity orderEntity = new OrderEntity(order);
        orderEntityRepository.save(orderEntity);
    }

    @Override
    public Optional<Order> findById(OrderId orderId) {
        return orderEntityRepository
                .findById(orderId.value())
                .map(OrderEntity::toOrder);
    }
}
