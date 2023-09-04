package xyz.proyectogshort.echo.order.domain;

import xyz.proyectogshort.shared.domain.AggregateRoot;

public class Order extends AggregateRoot {

    private final OrderId orderId;
    private final String title;
    private final OrderSource source;
    private final OrderSourceUrl sourceUrl;
    private final long mediaCount;
    private final String author;

    public Order(){
        orderId = null;
        title = null;
        source = null;
        sourceUrl = null;
        mediaCount = 0;
        author = null;
    }

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
            OrderSourceUrl sourceUrl,
            OrderInfo orderInfo
    ) {
        Order order = new Order(
                orderId,
                orderInfo.title(),
                orderInfo.source(),
                sourceUrl,
                orderInfo.mediaCount(),
                orderInfo.author()
        );

        order.record(new OrderCreatedEvent(orderId.value(), order.source.name(), sourceUrl.value()));

        return order;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public String getTitle() {
        return title;
    }

    public OrderSource getSource() {
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
