package xyz.proyectogshort.apps.echo.backend.controller.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.proyectogshort.echo.order.application.find.FindOrderQuery;
import xyz.proyectogshort.echo.order.application.find.OrderResponse;
import xyz.proyectogshort.shared.domain.bus.query.QueryBus;

@RestController
@RequestMapping("/orders")
public class OrderGetController {

    private final QueryBus bus;

    public OrderGetController(QueryBus bus) {
        this.bus = bus;
    }

    @GetMapping("{id}")
    public OrderResponse findOrder(@PathVariable String id) {
        return bus.ask(new FindOrderQuery(id));
    }
}
