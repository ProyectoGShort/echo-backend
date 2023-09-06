package xyz.proyectogshort.echo.media.domain;

import xyz.proyectogshort.echo.shared.domain.OrderId;
import xyz.proyectogshort.echo.shared.domain.OrderSource;
import xyz.proyectogshort.echo.shared.domain.OrderSourceUrl;
import xyz.proyectogshort.shared.domain.AggregateRoot;

public final class Media extends AggregateRoot {

    private final MediaId id;
    private final long mediaOrder;

    private String title;
    private String author;
    private MediaSourceUrl mediaSourceUrl;

    private final OrderId orderId;
    private final OrderSource orderSource;
    private final OrderSourceUrl orderSourceUrl;

    private MediaStatus mediaStatus;

    public Media(
            MediaId id,
            long mediaOrder,
            String title,
            String author,
            MediaSourceUrl mediaSourceUrl,
            MediaStatus mediaStatus,
            OrderId orderId,
            OrderSource orderSource,
            OrderSourceUrl orderSourceUrl
    ) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.mediaSourceUrl = mediaSourceUrl;
        this.mediaOrder = mediaOrder;
        this.mediaStatus = mediaStatus;
        this.orderId = orderId;
        this.orderSource = orderSource;
        this.orderSourceUrl = orderSourceUrl;
    }

    private Media(){
        id = null;
        mediaOrder = 0;
        orderId = null;
        orderSource = null;
        orderSourceUrl = null;
    }

    public static Media create(MediaId id, long mediaOrder, OrderId orderId, OrderSource source, OrderSourceUrl orderSourceUrl) {
        Media media = new Media(
                id,
                mediaOrder,
                null,
                null,
                null,
                MediaStatus.INITIALIZED,
                orderId,
                source,
                orderSourceUrl
        );

        media.record(new MediaCreatedEvent(media.id.value(), orderId.value()));

        return media;
    }

    public MediaId getId() {
        return id;
    }

    public long getMediaOrder() {
        return mediaOrder;
    }

    public OrderSource getOrderSource() {
        return orderSource;
    }

    public MediaStatus getMediaStatus() {
        return mediaStatus;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public MediaSourceUrl getMediaSourceUrl() {
        return mediaSourceUrl;
    }

    public String getMediaSourceUrlValue() {
        return mediaSourceUrl != null ? mediaSourceUrl.value() : null;
    }

    public void updateWithMediaInfo(MediaInfo mediaInfo) {
        title = mediaInfo.title();
        author = mediaInfo.author();
        mediaSourceUrl = mediaInfo.mediaSourceUrl();
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", mediaOrder=" + mediaOrder +
                '}';
    }
}
