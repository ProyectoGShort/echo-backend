package xyz.proyectogshort.echo.order.domain;

import xyz.proyectogshort.echo.shared.domain.OrderCreatedEvent;
import xyz.proyectogshort.echo.shared.domain.OrderId;
import xyz.proyectogshort.echo.shared.domain.OrderSourceUrl;
import xyz.proyectogshort.echo.shared.domain.Source;
import xyz.proyectogshort.shared.domain.AggregateRoot;

public final class Order extends AggregateRoot {

    private final OrderId id;
    private final String title;
    private final Source source;
    private final OrderSourceUrl sourceUrl;
    private final long mediaCount;
    private final String author;

    public Order(
            OrderId id,
            String title,
            Source source,
            OrderSourceUrl sourceUrl,
            long mediaCount,
            String author
    ) {
        this.id = id;
        this.title = title;
        this.source = source;
        this.sourceUrl = sourceUrl;
        this.mediaCount = mediaCount;
        this.author = author;
    }

    public static Order create(
            OrderId id,
            OrderSourceUrl sourceUrl,
            OrderInfo orderInfo
    ) {
        Order order = new Order(
                id,
                orderInfo.title(),
                orderInfo.source(),
                sourceUrl,
                orderInfo.mediaCount(),
                orderInfo.author()
        );

        order.record(new OrderCreatedEvent(
                id.value(),
                order.source.name(),
                sourceUrl.value(),
                order.mediaCount)
        );

        return order;
    }

    public OrderId getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Source getSource() {
        return source;
    }

    public OrderSourceUrl getSourceUrl() {
        return sourceUrl;
    }

    public long getMediaCount() {
        return mediaCount;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isPlaylist(){
        return mediaCount > 1;
    }
}
