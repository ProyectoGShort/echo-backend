package xyz.proyectogshort.echo.order.application.find;

import xyz.proyectogshort.echo.shared.application.FindOrderQuery;
import xyz.proyectogshort.echo.shared.domain.OrderId;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.query.QueryHandler;

@Service
public final class FindOrderQueryHandler implements QueryHandler<FindOrderQuery, OrderResponse> {

    private final OrderFinder orderFinder;

    public FindOrderQueryHandler(OrderFinder orderFinder) {
        this.orderFinder = orderFinder;
    }

    @Override
    public OrderResponse handle(FindOrderQuery query) {
        return orderFinder.find(new OrderId(query.id()));
    }
}
