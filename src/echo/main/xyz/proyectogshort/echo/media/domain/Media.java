package xyz.proyectogshort.echo.media.domain;

import xyz.proyectogshort.echo.shared.domain.OrderId;
import xyz.proyectogshort.echo.shared.domain.OrderSource;
import xyz.proyectogshort.echo.shared.domain.OrderSourceUrl;
import xyz.proyectogshort.shared.domain.AggregateRoot;

public final class Media extends AggregateRoot {

    private final MediaId id;
    private final long mediaOrder;
    private final OrderId orderId;
    private final OrderSource orderSource;
    private final OrderSourceUrl orderSourceUrl;

    private MediaStatus mediaStatus;

    public Media(MediaId id, long mediaOrder, MediaStatus mediaStatus, OrderId orderId, OrderSource orderSource, OrderSourceUrl orderSourceUrl) {
        this.id = id;
        this.mediaOrder = mediaOrder;
        this.mediaStatus = mediaStatus;
        this.orderId = orderId;
        this.orderSource = orderSource;
        this.orderSourceUrl = orderSourceUrl;
    }

    public static Media create(MediaId id, long mediaOrder, OrderId orderId, OrderSource source, OrderSourceUrl orderSourceUrl) {
        Media media = new Media(id, mediaOrder, MediaStatus.INITIALIZED, orderId, source, orderSourceUrl);

        media.record(new MediaCreatedEvent(media.id.value(), orderId.value()));

        return media;
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", mediaOrder=" + mediaOrder +
                '}';
    }
}
