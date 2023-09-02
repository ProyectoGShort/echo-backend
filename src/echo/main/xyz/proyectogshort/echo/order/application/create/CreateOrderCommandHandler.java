package xyz.proyectogshort.echo.order.application.create;

import xyz.proyectogshort.echo.order.domain.OrderId;
import xyz.proyectogshort.echo.order.domain.OrderSourceUrl;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.command.CommandHandler;

@Service
public final class CreateOrderCommandHandler implements CommandHandler<CreateOrderCommand> {
    private final OrderCreator orderCreator;

    public CreateOrderCommandHandler(OrderCreator orderCreator) {
        this.orderCreator = orderCreator;
    }

    @Override
    public void handle(CreateOrderCommand command) {
        orderCreator.create(new OrderId(command.id()), new OrderSourceUrl(command.sourceUrl()));
    }
}
