package xyz.proyectogshort.shared.infrastructure.command;

import org.springframework.context.ApplicationContext;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.command.*;

@SuppressWarnings({"rawtypes", "unchecked"})
@Service
public final class InMemoryCommandBus implements CommandBus {
    private final CommandMiddlewareChain middlewareChain;
    private final CommandHandlersInformation information;
    private final ApplicationContext         context;

    public InMemoryCommandBus(CommandMiddlewareChain middlewareChain, CommandHandlersInformation information, ApplicationContext context) {
        this.middlewareChain = middlewareChain;
        this.information = information;
        this.context     = context;
    }

    @Override
    public void dispatch(Command command) throws CommandHandlerExecutionError {
        middlewareChain.process(command);

        try {
            Class<? extends CommandHandler> commandHandlerClass = information.search(command.getClass());

            CommandHandler handler = context.getBean(commandHandlerClass);

            handler.handle(command);
        } catch (Throwable error) {
            throw new CommandHandlerExecutionError(error);
        }
    }
}
