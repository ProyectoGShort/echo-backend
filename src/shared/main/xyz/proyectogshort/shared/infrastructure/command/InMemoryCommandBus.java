package xyz.proyectogshort.shared.infrastructure.command;

import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.command.Command;
import xyz.proyectogshort.shared.domain.bus.command.CommandBus;
import xyz.proyectogshort.shared.domain.bus.command.CommandHandlerExecutionError;
import xyz.proyectogshort.shared.domain.bus.command.CommandMiddlewareChain;

@Service
public final class InMemoryCommandBus implements CommandBus {
    private final CommandMiddlewareChain middlewareChain;


    public InMemoryCommandBus(CommandMiddlewareChain middlewareChain) {
        this.middlewareChain = middlewareChain;
    }

    @Override
    public void dispatch(Command command) throws CommandHandlerExecutionError {
        middlewareChain.process(command);
    }
}
