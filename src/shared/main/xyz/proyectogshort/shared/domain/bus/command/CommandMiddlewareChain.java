package xyz.proyectogshort.shared.domain.bus.command;

import xyz.proyectogshort.shared.domain.Service;

import java.util.List;

@Service
public class CommandMiddlewareChain {
    private CommandMiddleware head;

    public CommandMiddlewareChain(List<CommandMiddleware> chain) {

        if (chain.isEmpty()) {
            return;
        }

        CommandMiddleware previous = chain.get(0);
        head = chain.remove(0);

        for (CommandMiddleware nextInChain: chain) {
            previous.setNext(nextInChain);
            previous = nextInChain;
        }
    }

    public void process(Command command) {
        if (head == null) {
            return;
        }

        head.process(command);
    }
}
