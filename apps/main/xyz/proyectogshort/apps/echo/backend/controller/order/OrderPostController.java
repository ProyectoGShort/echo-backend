package xyz.proyectogshort.apps.echo.backend.controller.order;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.proyectogshort.echo.order.application.create.CreateOrderCommand;
import xyz.proyectogshort.shared.domain.bus.command.CommandBus;

@RestController
@RequestMapping("/orders")
public final class OrderPostController {
    private final CommandBus bus;

    public OrderPostController(CommandBus bus) {
        this.bus = bus;
    }

    @PostMapping()
    public void create(@Valid @RequestBody OrderPostRequest request) {
        bus.dispatch(
                new CreateOrderCommand(request.id(), request.sourceUrl())
        );
    }
}
