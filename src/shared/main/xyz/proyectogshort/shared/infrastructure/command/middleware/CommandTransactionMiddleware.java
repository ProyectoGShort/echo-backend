package xyz.proyectogshort.shared.infrastructure.command.middleware;

import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.command.Command;
import xyz.proyectogshort.shared.domain.bus.command.CommandMiddleware;

@Service
@Order(1)
public class CommandTransactionMiddleware extends CommandMiddleware {

    private final ApplicationContext context;

    public CommandTransactionMiddleware(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void process(Command command) {
        PlatformTransactionManager transactionManager = getTransactionManager(command);

        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                next(command);
            }
        });
    }

    private PlatformTransactionManager getTransactionManager(Command command) {
        String module = getModuleName(command);
        String transactionName = String.format("%sTransactionManager", module);

        return context.getBean(transactionName, PlatformTransactionManager.class);
    }

    private static String getModuleName(Object object) {
        return object.getClass().getPackageName().split("\\.")[2];
    }
}
