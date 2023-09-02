package xyz.proyectogshort.echo.order.infrastructure.hibernate;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import xyz.proyectogshort.echo.order.domain.Order;
import xyz.proyectogshort.echo.order.domain.OrderId;
import xyz.proyectogshort.echo.order.domain.OrderRepository;
import xyz.proyectogshort.shared.domain.Service;

import java.util.Optional;

@Service
public class HibernateOrderRepository implements OrderRepository {

    private final ModelMapper modelMapper;
    private final OrderEntityRepository orderEntityRepository;

    public HibernateOrderRepository(OrderEntityRepository orderEntityRepository) {
        this.orderEntityRepository = orderEntityRepository;

        modelMapper = new ModelMapper();
        modelMapper
                .getConfiguration()
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);
    }

    @Override
    public void save(Order order) {
        OrderEntity orderEntity = modelMapper.map(order, OrderEntity.class);
        orderEntityRepository.save(orderEntity);
    }

    @Override
    public Optional<Order> findById(OrderId orderId) {
        return orderEntityRepository
                .findById(orderId.value())
                .map((entity) -> modelMapper.map(entity, Order.class));
    }
}
