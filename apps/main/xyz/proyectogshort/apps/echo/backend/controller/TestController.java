package xyz.proyectogshort.apps.echo.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.proyectogshort.shared.domain.bus.command.CommandBus;
import xyz.proyectogshort.test.test.application.create.CreateTestCommand;

@RestController
@RequestMapping("tests")
public class TestController {

    private final CommandBus commandBus;

    public TestController(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @PostMapping()
    public void create() {
        commandBus.dispatch(new CreateTestCommand());
    }
}
