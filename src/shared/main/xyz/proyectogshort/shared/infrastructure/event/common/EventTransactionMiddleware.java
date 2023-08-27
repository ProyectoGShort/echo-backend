package xyz.proyectogshort.shared.infrastructure.event.common;

import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import xyz.proyectogshort.shared.domain.Service;
import xyz.proyectogshort.shared.domain.bus.event.DomainEvent;
import xyz.proyectogshort.shared.domain.bus.event.EventMiddleware;

import java.util.Map;

@Service
@Order(1)
public class EventTransactionMiddleware extends EventMiddleware {

    private final ApplicationContext context;

    public EventTransactionMiddleware(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void process(DomainEvent event, Class<?> eventHandler, Map<String, Object> context) {
        PlatformTransactionManager transactionManager = getTransactionManager(eventHandler);

        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                next(event, eventHandler, context);
            }
        });
    }

    private PlatformTransactionManager getTransactionManager(Class<?> eventHandler) {
        String module = getModuleName(eventHandler);
        String transactionName = String.format("%sTransactionManager", module);

        return context.getBean(transactionName, PlatformTransactionManager.class);
    }

    private static String getModuleName(Class<?> eventHandlerClass) {
        return eventHandlerClass.getPackageName().split("\\.")[2];
    }

}
