package xyz.proyectogshort.shared.infrastructure.command.middleware;

import org.springframework.context.ApplicationContext;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.command.Command;
import xyz.proyectogshort.shared.domain.bus.command.CommandHandler;
import xyz.proyectogshort.shared.domain.bus.command.CommandHandlerExecutionError;
import xyz.proyectogshort.shared.domain.bus.command.CommandMiddleware;
import xyz.proyectogshort.shared.infrastructure.command.CommandHandlersInformation;

@SuppressWarnings({"rawtypes", "unchecked"})
@Service
public class CommandHandlerSelectorMiddleware extends CommandMiddleware {
    private final CommandHandlersInformation information;
    private final ApplicationContext context;

    public CommandHandlerSelectorMiddleware(CommandHandlersInformation information, ApplicationContext context) {
        this.information = information;
        this.context = context;
    }

    @Override
    public void process(Command command) {
        try {
            Class<? extends CommandHandler> commandHandlerClass = information.search(command.getClass());

            CommandHandler handler = context.getBean(commandHandlerClass);

            handler.handle(command);
        } catch (Throwable error) {
            throw new CommandHandlerExecutionError(error);
        }
    }
}
