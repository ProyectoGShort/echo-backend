package xyz.proyectogshort.apps.echo.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.proyectogshort.echo.order.application.create.CreateOrderCommand;
import xyz.proyectogshort.shared.domain.bus.command.CommandBus;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public final class OrderController {
    private final CommandBus bus;

    public OrderController(CommandBus bus) {
        this.bus = bus;
    }

    @PostMapping()
    public void create() {
        bus.dispatch(
                new CreateOrderCommand(UUID.randomUUID().toString())
        );
    }
}
