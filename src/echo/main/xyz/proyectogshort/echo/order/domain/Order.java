package xyz.proyectogshort.echo.order.domain;

import xyz.proyectogshort.shared.domain.AggregateRoot;

public class Order extends AggregateRoot {

    private final OrderId orderId;
    private final String title;
    private final OrderSource source;
    private final OrderSourceUrl sourceUrl;
    private final long mediaCount;
    private final String author;

    public Order(
            OrderId orderId,
            String title,
            OrderSource source,
            OrderSourceUrl sourceUrl,
            long mediaCount,
            String author
    ) {
        this.orderId = orderId;
        this.title = title;
        this.source = source;
        this.sourceUrl = sourceUrl;
        this.mediaCount = mediaCount;
        this.author = author;
    }

    public static Order create(
            OrderId orderId,
            String title,
            OrderSource source,
            OrderSourceUrl sourceUrl,
            long mediaCount,
            String author
    ) {
        Order order = new Order(orderId, title, source, sourceUrl, mediaCount, author);

        order.record(new OrderCreatedEvent(orderId.value(), order.source.name(), sourceUrl.value()));

        return order;
    }


    public boolean isPlaylist(){
        return mediaCount > 1;
    }
}
