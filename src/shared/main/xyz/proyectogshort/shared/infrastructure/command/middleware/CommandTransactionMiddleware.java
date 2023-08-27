package xyz.proyectogshort.shared.infrastructure.command.middleware;

import org.springframework.beans.factory.annotation.Qualifier;
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

    private final PlatformTransactionManager transactionManager;

    public CommandTransactionMiddleware(@Qualifier("echoTransactionManager") PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public void process(Command command) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                next(command);
            }
        });
    }
}
