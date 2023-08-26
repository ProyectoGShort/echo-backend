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

        head = chain.remove(0);

        for (CommandMiddleware nextInChain: chain) {
            head.setNext(nextInChain);
            head = nextInChain;
        }
    }

    public void process(Command command) {
        if (head == null) {
            return;
        }

        head.process(command);
    }
}
