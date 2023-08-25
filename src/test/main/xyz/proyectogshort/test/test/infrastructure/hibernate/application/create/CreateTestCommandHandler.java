package xyz.proyectogshort.test.test.infrastructure.hibernate.application.create;

import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.command.CommandHandler;

@Service
public class CreateTestCommandHandler implements CommandHandler<CreateTestCommand> {

    private final TestCreator testCreator;

    public CreateTestCommandHandler(TestCreator testCreator) {
        this.testCreator = testCreator;
    }

    @Override
    public void handle(CreateTestCommand command) {
        testCreator.create();
    }
}
