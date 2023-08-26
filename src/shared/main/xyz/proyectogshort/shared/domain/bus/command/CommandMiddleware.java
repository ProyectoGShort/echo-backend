package xyz.proyectogshort.shared.domain.bus.command;

public abstract class CommandMiddleware {
    private CommandMiddleware next;

    public CommandMiddleware() {}

    public void setNext(CommandMiddleware next) {
        this.next = next;
    }

    public abstract void process(Command command);

    protected void next(Command command) {
        if (next == null) {
            return;
        }

        next.process(command);
    }
}
