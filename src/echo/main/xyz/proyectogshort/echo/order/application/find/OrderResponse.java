package xyz.proyectogshort.echo.order.application.find;

import xyz.proyectogshort.echo.order.domain.Order;
import xyz.proyectogshort.shared.domain.bus.query.Response;

public record OrderResponse(
        String id,
        String title,
        String source,
        String sourceUrl,
        long mediaCount,
        String author
) implements Response {

    public static OrderResponse fromAggregate(Order order) {
        return new OrderResponse(
                order.getOrderId().value(),
                order.getTitle(),
                order.getSource().name(),
                order.getSourceUrl().value(),
                order.getMediaCount(),
                order.getAuthor()
        );
    }
}
